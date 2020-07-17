package pl.humberd.bugfixpreviewer.application.command.file.model

import org.springframework.web.multipart.MultipartFile

data class FileUploadCommand(
    val file: MultipartFile
)
