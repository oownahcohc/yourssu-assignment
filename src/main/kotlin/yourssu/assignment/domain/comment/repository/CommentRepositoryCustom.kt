package yourssu.assignment.domain.comment.repository

import yourssu.assignment.domain.comment.Comment

interface CommentRepositoryCustom {
    fun findByCommentIdAndArticleId(commentId: Long, articleId: Long): Comment?
}