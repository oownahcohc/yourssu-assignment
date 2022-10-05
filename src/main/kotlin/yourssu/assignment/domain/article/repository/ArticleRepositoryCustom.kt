package yourssu.assignment.domain.article.repository

import yourssu.assignment.domain.article.Article

interface ArticleRepositoryCustom {
    fun findByArticleId(articleId: Long): Article?
}