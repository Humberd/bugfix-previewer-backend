package pl.humberd.notesapp.application.common.asserts

import pl.humberd.bugfixpreviewer.application.exception.NotFoundException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
inline fun <reified T> ASSERT_NOT_NULL(
    entity: T?,
    id: String
) {
    contract {
        returns() implies (entity !== null)
    }

    if (entity === null) {
        throw NotFoundException(T::class, id)
    }
}
