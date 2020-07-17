package pl.humberd.bugfixpreviewer.infrastructure.http.sprint

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import pl.humberd.bugfixpreviewer.application.command.bugfix.BugfixCommandHandler
import pl.humberd.bugfixpreviewer.application.command.bugfix.model.BugfixCreateCommand
import pl.humberd.bugfixpreviewer.application.command.sprint.SprintCommandHandler
import pl.humberd.bugfixpreviewer.application.command.sprint.model.SprintCreateCommand
import pl.humberd.bugfixpreviewer.application.command.sprint.model.SprintDeleteCommand
import pl.humberd.bugfixpreviewer.application.command.sprint.model.SprintUpdateCommand
import pl.humberd.bugfixpreviewer.application.common.list_view.DefaultViewList
import pl.humberd.bugfixpreviewer.application.query.bugfix.BugfixQueryHandler
import pl.humberd.bugfixpreviewer.application.query.bugfix.model.BugfixView
import pl.humberd.bugfixpreviewer.application.query.sprint.SprintQueryHandler
import pl.humberd.bugfixpreviewer.application.query.sprint.model.SprintView
import pl.humberd.bugfixpreviewer.infrastructure.http.bugfix.model.BugfixCreateRequest
import pl.humberd.bugfixpreviewer.infrastructure.http.sprint.model.SprintCreateRequest
import pl.humberd.bugfixpreviewer.infrastructure.http.sprint.model.SprintUpdateRequest
import javax.validation.Valid
import kotlin.contracts.ExperimentalContracts

@RestController
@RequestMapping("/sprints")
@ExperimentalContracts
class SprintHttpController(
    private val sprintCommandHandler: SprintCommandHandler,
    private val sprintQueryHandler: SprintQueryHandler,
    private val bugfixQueryHandler: BugfixQueryHandler,
    private val bugfixCommandHandler: BugfixCommandHandler
) {

    @GetMapping
    fun readList(): DefaultViewList<SprintView> {
        return sprintQueryHandler.readList()
    }

    @GetMapping("/{id}")
    fun read(@PathVariable("id") id: String): SprintView {
        return sprintQueryHandler.read(id)
    }

    @PostMapping
    fun create(@RequestBody @Valid body: SprintCreateRequest): ResponseEntity<Void> {
        sprintCommandHandler.create(
            SprintCreateCommand(
                number = body.number,
                name = body.name
            )
        )

        return ResponseEntity.status(HttpStatus.CREATED).build<Void>()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody @Valid body: SprintUpdateRequest): ResponseEntity<Void> {
        sprintCommandHandler.update(
            SprintUpdateCommand(
                id = id,
                number = body.number,
                name = body.name
            )
        )

        return ResponseEntity.status(HttpStatus.ACCEPTED).build<Void>()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): ResponseEntity<Void> {
        sprintCommandHandler.delete(
            SprintDeleteCommand(
                id = id
            )
        )

        return ResponseEntity.ok().build<Void>()
    }

    @GetMapping("/{id}/bugfixes")
    fun readBugfixesList(@PathVariable("id") id: String): DefaultViewList<BugfixView> {
        return bugfixQueryHandler.readList(id)
    }

    @PostMapping("/{id}/bugfixes")
    fun createBugfix(@PathVariable("id") id: String, @RequestBody @Valid body: BugfixCreateRequest): ResponseEntity<Void> {
        bugfixCommandHandler.create(
            BugfixCreateCommand(
                sprintId = id,
                name = body.name,
                ticket = body.ticket,
                bugPreviewUrl = body.bugPrefixUrl,
                fixPreviewUrl = body.fixPrefixUrl
            )
        )

        return ResponseEntity.status(HttpStatus.CREATED).build<Void>()
    }
}
