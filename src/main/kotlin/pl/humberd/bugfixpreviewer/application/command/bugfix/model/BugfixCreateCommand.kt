package pl.humberd.bugfixpreviewer.application.command.bugfix.model

import pl.humberd.bugfixpreviewer.domain.entity.SprintId

data class BugfixCreateCommand(
    val sprintId: SprintId,
    val ticket: String,
    val name: String,
    val bugPreviewUrl: String?,
    val fixPreviewUrl: String?
)
