package yourssu.assignment.app.auth.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class ResignRequest(
    @field:NotBlank
    @Email
    val email: String,

    @field: NotBlank
    val password: String,
)