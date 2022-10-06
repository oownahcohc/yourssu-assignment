package yourssu.assignment.app.comment.service

import org.jetbrains.annotations.NotNull
import yourssu.assignment.common.exception.ResponseResult
import yourssu.assignment.common.exception.custom.NotFoundException
import yourssu.assignment.domain.comment.Comment
import yourssu.assignment.domain.comment.repository.CommentRepository

class CommentServiceUtils {
    companion object {

        @NotNull
        fun findByCommentIdAndArticleId(commentRepository: CommentRepository, commentId: Long, articleId: Long): Comment {
            return commentRepository.findByCommentIdAndArticleId(commentId, articleId)
                ?: throw NotFoundException(
                    String.format("해당 게시글 (%s) 의 댓글 (%s) 가 존재하지 않습니다", articleId, commentId),
                    ResponseResult.NOT_FOUND_COMMENT_EXCEPTION)
        }

    }
}