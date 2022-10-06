package yourssu.assignment.app.article.dto.response

import yourssu.assignment.domain.article.Article
import yourssu.assignment.domain.user.User

data class ArticleResponse(
    val articleId: Long,
    val email: String,
    val title: String,
    val content: String,
) {
    companion object{
        fun of(article: Article, user: User): ArticleResponse {
            return ArticleResponse(article.id!!, user.email, article.title, article.content)
        }
    }
}
