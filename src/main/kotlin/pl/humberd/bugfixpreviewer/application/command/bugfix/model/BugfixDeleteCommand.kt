package pl.humberd.bugfixpreviewer.application.command.bugfix.model

import pl.humberd.bugfixpreviewer.domain.entity.BugfixId

data class BugfixDeleteCommand(
    val id: BugfixId
)
