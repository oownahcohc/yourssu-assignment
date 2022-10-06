package yourssu.assignment.app.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import yourssu.assignment.app.auth.dto.request.ResignRequest
import yourssu.assignment.app.auth.dto.request.SignupRequest
import yourssu.assignment.app.auth.dto.response.SignupResponse
import yourssu.assignment.common.dto.ApiSuccessResponse
import yourssu.assignment.common.exception.ResponseResult.*
import javax.validation.Valid

@RestController
class AuthController(
    private val authService: AuthService
){

    /**
     * 회원가입
     */
    @PostMapping("/auth/signup")
    fun signup(@Valid @RequestBody request: SignupRequest): ResponseEntity<SignupResponse> {
        authService.signup(request).let {
            return ApiSuccessResponse.success(SUCCESS_CREATED_USER, SignupResponse.of(it.email, it.username))
        }
    }

    /**
     * 회원 탈퇴
     */
    @PostMapping("/auth/resign")
    fun resign(@Valid @RequestBody request: ResignRequest) {
        authService.resign(request)
    }
}