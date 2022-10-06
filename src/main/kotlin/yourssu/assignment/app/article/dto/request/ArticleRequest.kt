package yourssu.assignment.app.article.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class ArticleRequest(
    @field:NotBlank
    @Email
    val email: String,

    @field: NotBlank
    val password: String,

    @field: NotBlank
    val title: String,

    @field: NotBlank
    val content: String,
) {
    fun toCreateArticleDto(): CreateArticleDto {
        return CreateArticleDto(title, content)
    }
}
