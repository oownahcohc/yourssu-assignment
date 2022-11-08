package yourssu.assignment.app.article.dto.request

import yourssu.assignment.domain.article.Article
import yourssu.assignment.domain.user.entity.User

data class CreateArticleDto(
    val title: String,
    val content: String
) {
    fun toArticleEntity(user: User): Article {
        return Article.newInstance(user, title, content)
    }
}