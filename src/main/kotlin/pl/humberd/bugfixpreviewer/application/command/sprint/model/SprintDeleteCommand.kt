package pl.humberd.bugfixpreviewer.application.command.sprint.model

import pl.humberd.bugfixpreviewer.domain.entity.SprintId

data class SprintDeleteCommand(
    val id: SprintId
)
