package yourssu.assignment.app.article

import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import yourssu.assignment.app.article.dto.request.ArticleRequest
import yourssu.assignment.app.article.dto.request.DeleteArticleRequest
import yourssu.assignment.app.article.dto.response.ArticleResponse
import yourssu.assignment.app.article.service.ArticleService
import yourssu.assignment.app.auth.AuthService
import yourssu.assignment.common.dto.ApiSuccessResponse
import yourssu.assignment.common.exception.ResponseResult.*
import yourssu.assignment.config.interceptor.Auth
import javax.validation.Valid

@RestController
class ArticleController(
    private val authService: AuthService,
    private val articleService: ArticleService
) {

    @ApiOperation("게시글 등록")
    @Auth
    @PostMapping("/article/new")
    fun createArticle(@Valid @RequestBody request: ArticleRequest): ResponseEntity<ArticleResponse> {
        authService.getValidUser(request.email, request.password).let {
            return ApiSuccessResponse.success(SUCCESS_CREATED_ARTICLE, articleService.registerArticle(request.toCreateArticleDto(), it))
        }
    }

    @ApiOperation("게시글 수정")
    @Auth
    @PutMapping("/article/{articleId}/update")
    fun updateArticle(@Valid @RequestBody request: ArticleRequest, @PathVariable articleId: Long): ResponseEntity<ArticleResponse> {
        authService.getValidUser(request.email, request.password).let {
            return ApiSuccessResponse.success(SUCCESS_UPDATE_ARTICLE, articleService.updateArticle(request.toCreateArticleDto(), it, articleId))
        }
    }

    @ApiOperation("게시글 삭제")
    @Auth
    @DeleteMapping("/article/{articleId}")
    fun deleteArticle(@Valid @RequestBody request: DeleteArticleRequest, @PathVariable articleId: Long): ResponseEntity<String> {
        authService.getValidUser(request.email, request.password).let {
            articleService.deleteArticle(articleId)
            return ApiSuccessResponse.SUCCESS
        }
    }

}