package yourssu.assignment.app.comment.dto.response

import yourssu.assignment.domain.comment.Comment

data class CommentResponse(
    val commentId: Long,
    val email: String,
    val content: String
) {
    companion object {
        fun of(comment: Comment, email: String): CommentResponse {
            return CommentResponse(comment.id!!, email, comment.content)
        }
    }
}