package pl.humberd.bugfixpreviewer.application.query.bugfix

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import pl.humberd.bugfixpreviewer.application.common.list_view.DefaultViewList
import pl.humberd.bugfixpreviewer.application.common.list_view.ListViewExtra
import pl.humberd.bugfixpreviewer.application.query.bugfix.model.BugfixView
import pl.humberd.bugfixpreviewer.domain.entity.BugfixEntity
import pl.humberd.bugfixpreviewer.domain.entity.SprintId
import pl.humberd.bugfixpreviewer.domain.repository.BugfixRepository
import kotlin.contracts.ExperimentalContracts

@Service
@ExperimentalContracts
class BugfixQueryHandler(
    private val bugfixRepository: BugfixRepository
)  {
    fun readList(sprintId: SprintId): DefaultViewList<BugfixView> {
        val page = bugfixRepository.findAllBySprintId(sprintId, Pageable.unpaged())

        return DefaultViewList(
            data = page.content.map { mapToView(it) },
            extra = ListViewExtra.from(page)
        )
    }

    fun mapToView(entity: BugfixEntity): BugfixView {
        return BugfixView(
            id = entity.id,
            sprintId = entity.sprintId,
            name = entity.name,
            ticket = entity.ticket,
            fixPreviewUrl = entity.fixPreviewUrl,
            bugPreviewUrl = entity.bugPreviewUrl
        )
    }
}
