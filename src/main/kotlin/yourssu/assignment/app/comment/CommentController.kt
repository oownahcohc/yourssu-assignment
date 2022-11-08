package yourssu.assignment.app.comment

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import yourssu.assignment.app.auth.AuthService
import yourssu.assignment.app.comment.dto.request.CommentRequest
import yourssu.assignment.app.comment.dto.request.DeleteCommentRequest
import yourssu.assignment.app.comment.dto.response.CommentResponse
import yourssu.assignment.app.comment.service.CommentService
import yourssu.assignment.common.dto.ApiSuccessResponse
import yourssu.assignment.common.exception.ResponseResult
import yourssu.assignment.config.interceptor.Auth
import javax.validation.Valid
import kotlin.time.measureTime

@RestController
class CommentController(
    private val authService: AuthService,
    private val commentService: CommentService
) {

    /**
     * 댓글 등록
     */
    @Auth
    @PostMapping("/{articleId}/comment/new")
    fun createComment(@Valid @RequestBody request: CommentRequest, @PathVariable articleId: Long): ResponseEntity<CommentResponse> {
        authService.getValidUser(request.email, request.password).let {
            return ApiSuccessResponse.success(ResponseResult.SUCCESS_CREATE_COMMENT,
                commentService.registerComment(request.toCommentDto(), it, articleId))
        }
    }

    /**
     * 댓글 수정
     */
    @Auth
    @PutMapping("/{articleId}/comment/{commentId}/update")
    fun updateComment(@Valid @RequestBody request: CommentRequest,
                      @PathVariable articleId: Long,
                      @PathVariable commentId: Long
    ): ResponseEntity<CommentResponse> {
        authService.getValidUser(request.email, request.password).let {
            return ApiSuccessResponse.success(ResponseResult.SUCCESS_UPDATE_COMMENT,
                commentService.updateComment(request.toCommentDto(), it, articleId, commentId))
        }
    }

    /**
     * 댓글 삭제
     */
    @Auth
    @DeleteMapping("/{articleId}/comment/{commentId}")
    fun deleteComment(@Valid @RequestBody request: DeleteCommentRequest,
                      @PathVariable articleId: Long,
                      @PathVariable commentId: Long
    ): ResponseEntity<String> {
        authService.getValidUser(request.email, request.password).let {
            commentService.deleteComment(articleId, commentId)
            return ApiSuccessResponse.SUCCESS
        }
    }

}