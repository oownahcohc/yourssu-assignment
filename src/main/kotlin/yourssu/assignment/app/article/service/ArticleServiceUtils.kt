package yourssu.assignment.app.article.service

import org.jetbrains.annotations.NotNull
import yourssu.assignment.common.exception.ResponseResult
import yourssu.assignment.common.exception.custom.NotFoundException
import yourssu.assignment.domain.article.Article
import yourssu.assignment.domain.article.repository.ArticleRepository

class ArticleServiceUtils {

    companion object {

        @NotNull
        fun findByArticleId(articleRepository: ArticleRepository, articleId: Long): Article {
            return articleRepository.findByArticleId(articleId)
                ?: throw NotFoundException(
                    String.format("존재하지 않는 게시물 (%s) 입니다", articleId),
                    ResponseResult.NOT_FOUND_ARTICLE_EXCEPTION)
        }

    }
}