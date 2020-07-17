package pl.humberd.bugfixpreviewer.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

typealias SprintId = String

@Entity
@Table(schema = "public", name = "sprint")
class SprintEntity(
    @Id
    @Column(name = "id")
    val id: SprintId,

    @Column(name = "number")
    var number: Int,

    @Column(name = "name")
    var name: String
)
