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
            url = awsS3Bridge.uploadFile(command.file)
        )
    }
}
