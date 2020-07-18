package pl.humberd.bugfixpreviewer.application.command.file

import org.springframework.stereotype.Service
import pl.humberd.bugfixpreviewer.application.command.file.model.FileUploadCommand
import pl.humberd.bugfixpreviewer.application.command.file.model.FileUploadResult
import pl.humberd.bugfixpreviewer.application.thirdparty.AWSS3Bridge

@Service
class FileCommandHandler(
    private val awsS3Bridge: AWSS3Bridge
) {

    fun upload(command: FileUploadCommand): FileUploadResult {
        return FileUploadResult(
//            url = awsS3Bridge.uploadFile(command.file)
            url = "https://testmobilehub-hosting-mobilehub-825908255.s3.eu-central-1.amazonaws.com/example-video-1.mp4"
        )
    }
}
