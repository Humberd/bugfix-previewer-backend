package pl.humberd.bugfixpreviewer.application.query.sprint

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.humberd.bugfixpreviewer.application.common.list_view.DefaultViewList
import pl.humberd.bugfixpreviewer.application.common.list_view.ListViewExtra
import pl.humberd.bugfixpreviewer.application.query.sprint.model.SprintView
import pl.humberd.bugfixpreviewer.domain.entity.BugfixEntity
import pl.humberd.bugfixpreviewer.domain.entity.SprintEntity
import pl.humberd.bugfixpreviewer.domain.entity.SprintId
import pl.humberd.bugfixpreviewer.domain.repository.BugfixRepository
import pl.humberd.bugfixpreviewer.domain.repository.SprintRepository
import pl.humberd.notesapp.application.common.asserts.ASSERT_NOT_NULL
import kotlin.contracts.ExperimentalContracts

@ExperimentalContracts
@Service
class SprintQueryHandler(
    private val sprintRepository: SprintRepository,
    private val bugfixRepository: BugfixRepository
) {
    fun readList(): DefaultViewList<SprintView> {
        val page = sprintRepository.findAll(Pageable.unpaged())
        val allBugfixes = bugfixRepository.findAll()
        val groupedBugfixes = allBugfixes.groupBy { it.sprintId }

        return DefaultViewList(
            data = page.content.map { mapToView(it, groupedBugfixes.getOrDefault(it.id, emptyList())) },
            extra = ListViewExtra.from(page)
        )
    }

    fun read(id: SprintId): SprintView {
        val sprint = sprintRepository.findByIdOrNull(id)
        ASSERT_NOT_NULL(sprint, id)

        val bugfixes = bugfixRepository.findAllBySprintId(id)

        return mapToView(sprint, bugfixes)
    }

    fun mapToView(
        entity: SprintEntity,
        groupedBugfixes: List<BugfixEntity>
    ): SprintView {
        return SprintView(
            id = entity.id,
            name = entity.name,
            number = entity.number,
            resolvedBugfixCount = groupedBugfixes.count { it.bugPreviewUrl != null && it.fixPreviewUrl != null },
            totalBugfixCount = groupedBugfixes.count()
        )
    }
}
