package pl.humberd.bugfixpreviewer.infrastructure.http.sprint.model

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class SprintUpdateRequest {
    @Min(0)
    var number: Int = 0

    @NotBlank
    lateinit var name: String
}
