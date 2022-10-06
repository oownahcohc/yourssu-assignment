package yourssu.assignment.app.article.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yourssu.assignment.app.article.dto.request.CreateArticleDto
import yourssu.assignment.app.article.dto.response.ArticleResponse
import yourssu.assignment.common.exception.custom.NotFoundException
import yourssu.assignment.domain.article.repository.ArticleRepository
import yourssu.assignment.domain.comment.repository.CommentRepository
import yourssu.assignment.domain.user.User

@Service
class ArticleService(private val articleRepository: ArticleRepository) {

    @Transactional
    fun registerArticle(request: CreateArticleDto, user: User): ArticleResponse {
        articleRepository.save(request.toArticleEntity(user)).let {
            user.addArticle(it)
            return ArticleResponse.of(it, user)
        }
    }

    @Transactional
    fun updateArticle(request: CreateArticleDto, user: User, articleId: Long): ArticleResponse {
        ArticleServiceUtils.findByArticleId(articleRepository, articleId).let {
            it.updateTitleAndContent(request.title, request.content)
            return ArticleResponse.of(it, user)
        }
    }

    @Transactional
    fun deleteArticle(articleId: Long) {
        with(ArticleServiceUtils.findByArticleId(articleRepository, articleId)) {
            this.deleteComment()
            articleRepository.delete(this)
        }
    }

}