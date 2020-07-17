package pl.humberd.bugfixpreviewer.application.command.sprint

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.humberd.bugfixpreviewer.application.command.sprint.model.SprintCreateCommand
import pl.humberd.bugfixpreviewer.application.command.sprint.model.SprintUpdateCommand
import pl.humberd.bugfixpreviewer.domain.common.IdGenerator
import pl.humberd.bugfixpreviewer.domain.entity.SprintEntity
import pl.humberd.bugfixpreviewer.domain.repository.SprintRepository
import pl.humberd.notesapp.application.common.asserts.ASSERT_NOT_NULL
import kotlin.contracts.ExperimentalContracts

@Service
@Transactional
@ExperimentalContracts
class SprintCommandHandler(
    private val sprintRepository: SprintRepository
) {
    fun create(command: SprintCreateCommand) {
        sprintRepository.save(
            SprintEntity(
                id = IdGenerator.random(SprintEntity::class),
                number = command.number,
                name = command.name
            )
        )
    }

    fun update(command: SprintUpdateCommand) {
        val sprint = sprintRepository.findByIdOrNull(command.id)
        ASSERT_NOT_NULL(sprint, command.id)

        sprint.also {
            it.number = command.number
            it.name = command.name
        }
    }
}
