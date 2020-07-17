package pl.humberd.bugfixpreviewer.infrastructure.http.bugfix

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import pl.humberd.bugfixpreviewer.application.command.bugfix.BugfixCommandHandler
import pl.humberd.bugfixpreviewer.application.command.bugfix.model.BugfixDeleteCommand
import pl.humberd.bugfixpreviewer.application.command.bugfix.model.BugfixUpdateCommand
import pl.humberd.bugfixpreviewer.application.common.list_view.DefaultViewList
import pl.humberd.bugfixpreviewer.application.query.bugfix.BugfixQueryHandler
import pl.humberd.bugfixpreviewer.application.query.bugfix.model.BugfixView
import pl.humberd.bugfixpreviewer.domain.entity.BugfixEntity
import pl.humberd.bugfixpreviewer.infrastructure.http.bugfix.model.BugfixUpdateRequest
import kotlin.contracts.ExperimentalContracts

@RestController
@RequestMapping("/bugfixes")
@ExperimentalContracts
class BugfixHttpController(
    private val bugfixCommandHandler: BugfixCommandHandler,
    private val bugfixQueryHandler: BugfixQueryHandler
) {

    @GetMapping("/{id}")
    fun read(@PathVariable("id") id: String): BugfixEntity {
        return bugfixQueryHandler.read(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody body: BugfixUpdateRequest): ResponseEntity<Void> {
        bugfixCommandHandler.update(
            BugfixUpdateCommand(
                id = id,
                name = body.name,
                ticket = body.ticket,
                bugPreviewUrl = body.bugPrefixUrl,
                fixPreviewUrl = body.fixPrefixUrl
            )
        )

        return ResponseEntity.status(HttpStatus.ACCEPTED).build<Void>()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): ResponseEntity<Void> {
        bugfixCommandHandler.delete(
            BugfixDeleteCommand(
                id = id
            )
        )

        return ResponseEntity.ok().build<Void>()
    }


}
