package yourssu.assignment.app.comment.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yourssu.assignment.app.article.service.ArticleServiceUtils
import yourssu.assignment.app.comment.dto.request.CommentDto
import yourssu.assignment.app.comment.dto.response.CommentResponse
import yourssu.assignment.domain.article.repository.ArticleRepository
import yourssu.assignment.domain.comment.repository.CommentRepository
import yourssu.assignment.domain.user.entity.User

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val articleRepository: ArticleRepository
) {

    @Transactional
    fun registerComment(request: CommentDto, user: User, articleId: Long): CommentResponse {
        val article = ArticleServiceUtils.findByArticleId(articleRepository, articleId)
        val comment = commentRepository.save(request.toCommentEntity(user, article))
        article.addComment(comment)
        user.addComment(comment)
        return CommentResponse.of(comment, user.email)
    }

    @Transactional
    fun updateComment(request: CommentDto, user: User, articleId: Long, commentId: Long): CommentResponse {
        val comment = CommentServiceUtils.findByCommentIdAndArticleId(commentRepository, commentId, articleId)
        comment.updateContent(request.content)
        return CommentResponse.of(comment, user.email)
    }

    @Transactional
    fun deleteComment(articleId: Long, commentId: Long) {
        commentRepository.delete(CommentServiceUtils.findByCommentIdAndArticleId(commentRepository, commentId, articleId))
    }

}