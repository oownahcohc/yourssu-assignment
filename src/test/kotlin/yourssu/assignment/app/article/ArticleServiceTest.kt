package yourssu.assignment.app.article

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.spyk
import yourssu.assignment.app.article.service.ArticleService
import yourssu.assignment.domain.article.repository.ArticleRepository

class ArticleServiceTest: BehaviorSpec({
    val articleRepository = mockk<ArticleRepository>(relaxed = true)

    val articleService: ArticleService = spyk(ArticleService(articleRepository))

    Given("게시글을 등록하는 경우") {

    }
})