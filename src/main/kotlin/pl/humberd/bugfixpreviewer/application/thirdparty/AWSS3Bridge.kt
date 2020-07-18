package pl.humberd.bugfixpreviewer.application.thirdparty

import com.amazonaws.AmazonServiceException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.time.LocalDateTime


@Service
class AWSS3Bridge {
    @Autowired
    private lateinit var amazonS3: AmazonS3

    @Value("\${aws.s3.bucket}")
    private lateinit var bucketName: String

    // @Async annotation ensures that the method is executed in a different background thread
    // but not consume the main thread.
    @Async
    fun uploadFile(multipartFile: MultipartFile): String {
        LOGGER.info("File upload in progress.")
        try {
            val file = convertMultiPartFileToFile(multipartFile)
            val response = uploadFileToS3Bucket(bucketName, file)
            LOGGER.info("File upload is completed.")
            file.delete() // To remove the file locally created in the project folder.

            return response.toString()
        } catch (ex: AmazonServiceException) {
            LOGGER.info("File upload is failed.")
            LOGGER.error("Error= {} while uploading file.", ex.message)

            throw ex
        }
    }

    private fun convertMultiPartFileToFile(multipartFile: MultipartFile): File {
        val file = File(multipartFile.originalFilename)
        try {
            FileOutputStream(file).use { outputStream -> outputStream.write(multipartFile.bytes) }
        } catch (ex: IOException) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.message)

            throw ex
        }
        return file
    }

    private fun uploadFileToS3Bucket(bucketName: String?, file: File): URL {
        val uniqueFileName = LocalDateTime.now().toString() + "_" + file.name
        LOGGER.info("Uploading file with name= $uniqueFileName")
        val putObjectRequest = PutObjectRequest(bucketName, uniqueFileName, file)
            .withCannedAcl(CannedAccessControlList.PublicRead)
        amazonS3.putObject(putObjectRequest)

        return amazonS3.getUrl(bucketName, uniqueFileName)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AWSS3Bridge::class.java)
    }
}
