package yourssu.assignment.app.comment

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.spyk
import yourssu.assignment.app.comment.service.CommentService
import yourssu.assignment.domain.article.repository.ArticleRepository
import yourssu.assignment.domain.comment.repository.CommentRepository

class CommentServiceTest: BehaviorSpec({
  val commentRepository = mockk<CommentRepository>(relaxed = true)
  val articleRepository = mockk<ArticleRepository>(relaxed = true)

  val commentService: CommentService = spyk(CommentService(commentRepository, articleRepository))


})