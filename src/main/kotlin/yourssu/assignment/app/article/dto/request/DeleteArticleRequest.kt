package yourssu.assignment.app.article.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class DeleteArticleRequest(
    @field:NotBlank
    @Email
    val email: String,

    @field: NotBlank
    val password: String,
)
