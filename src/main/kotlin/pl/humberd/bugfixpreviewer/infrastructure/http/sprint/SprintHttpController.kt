package pl.humberd.bugfixpreviewer.infrastructure.http.sprint

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.humberd.bugfixpreviewer.application.command.sprint.SprintCommandHandler
import pl.humberd.bugfixpreviewer.application.command.sprint.model.SprintCreateCommand
import pl.humberd.bugfixpreviewer.application.command.sprint.model.SprintDeleteCommand
import pl.humberd.bugfixpreviewer.application.command.sprint.model.SprintUpdateCommand
import pl.humberd.bugfixpreviewer.application.common.list_view.DefaultViewList
import pl.humberd.bugfixpreviewer.application.query.sprint.SprintQueryHandler
import pl.humberd.bugfixpreviewer.application.query.sprint.model.SprintView
import pl.humberd.bugfixpreviewer.infrastructure.http.sprint.model.SprintCreateRequest
import pl.humberd.bugfixpreviewer.infrastructure.http.sprint.model.SprintUpdateRequest
import kotlin.contracts.ExperimentalContracts

@RestController
@RequestMapping("/sprints")
@ExperimentalContracts
class SprintHttpController(
    private val sprintCommandHandler: SprintCommandHandler,
    private val sprintQueryHandler: SprintQueryHandler
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
    fun create(@RequestBody body: SprintCreateRequest): ResponseEntity<Void> {
        sprintCommandHandler.create(
            SprintCreateCommand(
                number = body.number,
                name = body.name
            )
        )

        return ResponseEntity.status(HttpStatus.CREATED).build<Void>()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody body: SprintUpdateRequest): ResponseEntity<Void> {
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
}
