package pl.humberd.bugfixpreviewer.application.query.bugfix

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.humberd.bugfixpreviewer.application.common.list_view.DefaultViewList
import pl.humberd.bugfixpreviewer.application.common.list_view.ListViewExtra
import pl.humberd.bugfixpreviewer.application.query.bugfix.model.BugfixView
import pl.humberd.bugfixpreviewer.domain.entity.BugfixEntity
import pl.humberd.bugfixpreviewer.domain.entity.SprintId
import pl.humberd.bugfixpreviewer.domain.repository.BugfixRepository
import pl.humberd.notesapp.application.common.asserts.ASSERT_NOT_NULL
import kotlin.contracts.ExperimentalContracts

@Service
@ExperimentalContracts
class BugfixQueryHandler(
    private val bugfixRepository: BugfixRepository
) {
    fun readList(sprintId: SprintId): DefaultViewList<BugfixView> {
        val page = bugfixRepository.findAllBySprintId(sprintId, PageRequest.of(0, 999, Sort.by(Sort.Order.asc("name"))))

        return DefaultViewList(
            data = page.content.map { mapToView(it) },
            extra = ListViewExtra.from(page)
        )
    }

    fun read(id: String): BugfixEntity {
        val bugfix = bugfixRepository.findByIdOrNull(id);
        ASSERT_NOT_NULL(bugfix, id)

        return bugfix
    }

    fun mapToView(entity: BugfixEntity): BugfixView {
        return BugfixView(
            id = entity.id,
            sprintId = entity.sprintId,
            name = entity.name,
            ticket = entity.ticket,
            fixPreviewUrl = if (entity.fixPreviewUrl != null) "http://localhost:8081/example-video-1.mp4" else null,
            bugPreviewUrl = if (entity.bugPreviewUrl != null) "http://localhost:8081/example-video-1.mp4" else null
        )
    }
}
