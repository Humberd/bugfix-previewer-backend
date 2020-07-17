package pl.humberd.bugfixpreviewer.application.command.bugfix

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.humberd.bugfixpreviewer.application.command.bugfix.model.BugfixCreateCommand
import pl.humberd.bugfixpreviewer.application.command.bugfix.model.BugfixUpdateCommand
import pl.humberd.bugfixpreviewer.domain.common.IdGenerator
import pl.humberd.bugfixpreviewer.domain.entity.BugfixEntity
import pl.humberd.bugfixpreviewer.domain.repository.BugfixRepository
import pl.humberd.notesapp.application.common.asserts.ASSERT_NOT_NULL
import kotlin.contracts.ExperimentalContracts

@Service
@Transactional
@ExperimentalContracts
class BugfixCommandHandler(
    private val bugfixRepository: BugfixRepository
) {
    fun create(command: BugfixCreateCommand) {
        bugfixRepository.save(
            BugfixEntity(
                id = IdGenerator.random(BugfixEntity::class),
                sprintId = command.sprintId,
                name = command.name,
                ticket = command.ticket,
                bugPreviewUrl = command.bugPreviewUrl,
                fixPreviewUrl = command.fixPreviewUrl
            )
        )
    }

    fun update(command: BugfixUpdateCommand) {
        val bugfix = bugfixRepository.findByIdOrNull(command.id);
        ASSERT_NOT_NULL(bugfix, command.id)

        bugfix.also {
            it.ticket = command.ticket
            it.name = command.name
            it.bugPreviewUrl = command.bugPreviewUrl
            it.fixPreviewUrl = command.fixPreviewUrl
        }
    }
}
