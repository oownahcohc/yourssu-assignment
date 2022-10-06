package yourssu.assignment.app.comment.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CommentRequest(
    @field:NotBlank
    @Email
    val email: String,

    @field: NotBlank
    val password: String,

    @field: NotBlank
    val content: String
) {
    fun toCommentDto(): CommentDto {
        return CommentDto(content)
    }
}