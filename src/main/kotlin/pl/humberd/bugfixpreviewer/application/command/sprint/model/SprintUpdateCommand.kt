package pl.humberd.bugfixpreviewer.application.command.sprint.model

import pl.humberd.bugfixpreviewer.domain.entity.SprintId

data class SprintUpdateCommand(
    val id: SprintId,
    val number: Int,
    val name: String
)
