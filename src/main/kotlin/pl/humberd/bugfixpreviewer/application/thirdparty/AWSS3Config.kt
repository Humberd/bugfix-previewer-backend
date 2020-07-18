package pl.humberd.bugfixpreviewer.application.thirdparty

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AWSS3Config {
    // Access key id will be read from the application.properties file during the application intialization.
    @Value("\${aws.access_key_id}")
    lateinit var accessKeyId: String

    // Secret access key will be read from the application.properties file during the application intialization.
    @Value("\${aws.secret_access_key}")
    lateinit var secretAccessKey: String

    // Region will be read from the application.properties file  during the application intialization.
    @Value("\${aws.s3.region}")
    lateinit var region: String

    // Get AmazonS3 client and return the s3Client object.
    @get:Bean
    val amazonS3Cient: AmazonS3
        get() {
            val basicAWSCredentials = BasicAWSCredentials(accessKeyId, secretAccessKey)
            // Get AmazonS3 client and return the s3Client object.
            return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(AWSStaticCredentialsProvider(basicAWSCredentials))
                .build()
        }
}
