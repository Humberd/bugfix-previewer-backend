package pl.humberd.bugfixpreviewer.application.command.bugfix.model

import pl.humberd.bugfixpreviewer.domain.entity.BugfixId


data class BugfixUpdateCommand(
    val id: BugfixId,
    val ticket: String,
    val name: String,
    val bugPreviewUrl: String?,
    val fixPreviewUrl: String?
)
