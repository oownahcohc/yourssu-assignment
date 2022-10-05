package yourssu.assignment.domain.article.repository

import org.springframework.data.jpa.repository.JpaRepository
import yourssu.assignment.domain.article.Article

interface ArticleRepository: JpaRepository<Article, Long>, ArticleRepositoryCustom {
}