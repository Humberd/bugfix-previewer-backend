package pl.humberd.bugfixpreviewer.application.query.bugfix.model

import pl.humberd.bugfixpreviewer.domain.entity.BugfixId
import pl.humberd.bugfixpreviewer.domain.entity.SprintId

data class BugfixView(
    val id: BugfixId,
    val sprintId: SprintId,
    val ticket: String,
    val name: String,
    val bugPreviewUrl: String?,
    val fixPreviewUrl: String?
)
