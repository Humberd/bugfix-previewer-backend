package pl.humberd.bugfixpreviewer.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.humberd.bugfixpreviewer.domain.entity.BugfixEntity
import pl.humberd.bugfixpreviewer.domain.entity.BugfixId
import pl.humberd.bugfixpreviewer.domain.entity.SprintId

@Repository
interface BugfixRepository : JpaRepository<BugfixEntity, BugfixId> {
    fun findAllBySprintId(sprintId: SprintId, pageable: Pageable): Page<BugfixEntity>
}
