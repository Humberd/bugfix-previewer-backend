package pl.humberd.bugfixpreviewer.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

typealias BugfixId = String

@Entity
@Table(schema = "public", name = "bugfix")
class BugfixEntity(
    @Id
    @Column(name = "id")
    val id: BugfixId,

    @Column(name = "sprint_id")
    val sprintId: SprintId,

    @Column(name = "ticket")
    val ticket: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "bug_preview_url")
    val bugPreviewUrl: String?,

    @Column(name = "fix_preview_url")
    val fixPreviewUrl: String?
)
