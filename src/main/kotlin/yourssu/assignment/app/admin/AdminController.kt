package yourssu.assignment.app.admin

import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import yourssu.assignment.common.dto.ApiSuccessResponse
import yourssu.assignment.common.exception.ResponseResult
import yourssu.assignment.config.interceptor.Auth
import yourssu.assignment.domain.user.entity.UserRole

@RestController
class AdminController(private val adminService: AdminService) {

    @ApiOperation("관리자 컨트롤러 - 회원 조회")
    @Auth(role = UserRole.ADMIN)
    @GetMapping("/show")
    fun showUser(@RequestParam(required = false) email: String?,
                 @RequestParam(required = false) createdAtStart: String?,
                 @RequestParam(required = false) createdAtEnd: String?,
    ): ResponseEntity<Any> {
        var response: ResponseEntity<Any>? = null
        response = if (email != null && createdAtStart == null && createdAtEnd == null) {
            ApiSuccessResponse.success(ResponseResult.SUCCESS_OK, adminService.retrieveUserByEmail(email))
        } else {
            ApiSuccessResponse.success(ResponseResult.SUCCESS_OK, adminService.retrieveUser(createdAtStart, createdAtEnd))
        }
        return response
    }
}