package pl.humberd.bugfixpreviewer.application.command.bugfix.model

import pl.humberd.bugfixpreviewer.domain.entity.BugfixId
import pl.humberd.bugfixpreviewer.domain.entity.SprintId

data class BugfixCreateCommand(
    val id: BugfixId,
    val sprintId: SprintId,
    val ticket: String,
    val name: String,
    val bugPreviewUrl: String?,
    val fixPreviewUrl: String?
)
