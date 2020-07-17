package pl.humberd.bugfixpreviewer.infrastructure.http.bugfix.model

import pl.humberd.bugfixpreviewer.domain.entity.SprintId
import javax.validation.constraints.NotBlank

class BugfixUpdateRequest {
    @NotBlank
    lateinit var ticket: String

    @NotBlank
    lateinit var name: String

    var bugPrefixUrl: String? = null
    var fixPrefixUrl: String? = null
}
