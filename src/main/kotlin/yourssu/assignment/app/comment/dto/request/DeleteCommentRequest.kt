package yourssu.assignment.app.comment.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class DeleteCommentRequest(
    @field:NotBlank
    @Email
    val email: String,

    @field: NotBlank
    val password: String,
)