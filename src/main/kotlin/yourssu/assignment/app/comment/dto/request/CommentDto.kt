package yourssu.assignment.app.comment.dto.request

import yourssu.assignment.domain.article.Article
import yourssu.assignment.domain.comment.Comment
import yourssu.assignment.domain.user.entity.User

data class CommentDto(val content: String) {
    fun toCommentEntity(user: User, article: Article): Comment {
        return Comment.newInstance(user, article, content)
    }
}
