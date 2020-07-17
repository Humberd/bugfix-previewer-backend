package pl.humberd.bugfixpreviewer.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.humberd.bugfixpreviewer.domain.entity.BugfixEntity
import pl.humberd.bugfixpreviewer.domain.entity.BugfixId

@Repository
interface BugfixRepository : JpaRepository<BugfixEntity, BugfixId>
