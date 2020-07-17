package pl.humberd.bugfixpreviewer.infrastructure.http.file

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import pl.humberd.bugfixpreviewer.application.command.file.FileCommandHandler
import pl.humberd.bugfixpreviewer.application.command.file.model.FileUploadCommand
import pl.humberd.bugfixpreviewer.application.command.file.model.FileUploadResult

@RestController
@RequestMapping("/files")
class FilesHttpController(
    private val fileCommandHandler: FileCommandHandler
) {

    @PostMapping
    fun upload(@RequestParam("files") file: MultipartFile): ResponseEntity<FileUploadResult> {
        val result = fileCommandHandler.upload(
            FileUploadCommand(
                file = file
            )
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }
}
