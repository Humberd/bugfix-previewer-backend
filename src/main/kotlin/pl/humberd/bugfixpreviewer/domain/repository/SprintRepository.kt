package pl.humberd.bugfixpreviewer.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.humberd.bugfixpreviewer.domain.entity.SprintEntity
import pl.humberd.bugfixpreviewer.domain.entity.SprintId

@Repository
interface SprintRepository : JpaRepository<SprintEntity, SprintId>
