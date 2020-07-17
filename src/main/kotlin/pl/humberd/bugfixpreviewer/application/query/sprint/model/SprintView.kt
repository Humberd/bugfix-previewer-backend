package pl.humberd.bugfixpreviewer.application.query.sprint.model

import pl.humberd.bugfixpreviewer.domain.entity.SprintId

data class SprintView(
    val id: SprintId,
    val number: Int,
    val name: String,
    val totalBugfixCount: Int,
    val resolvedBugfixCount: Int
)
