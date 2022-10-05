package yourssu.assignment.domain.article.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import yourssu.assignment.domain.article.Article
import yourssu.assignment.domain.article.QArticle.*

class ArticleRepositoryCustomImpl(
    private val query: JPAQueryFactory
): ArticleRepositoryCustom {
    override fun findByArticleId(articleId: Long): Article? {
        return query
            .selectFrom(article)
            .where(article.id.eq(articleId))
            .fetchOne()
    }
}