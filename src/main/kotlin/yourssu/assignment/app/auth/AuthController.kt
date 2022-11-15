package yourssu.assignment.app.auth

import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import yourssu.assignment.app.auth.dto.request.LoginRequest
import yourssu.assignment.app.auth.dto.request.ResignRequest
import yourssu.assignment.app.auth.dto.request.SignupRequest
import yourssu.assignment.app.auth.dto.response.LoginResponse
import yourssu.assignment.app.auth.dto.response.SignupResponse
import yourssu.assignment.app.auth.dto.response.TokenResponse
import yourssu.assignment.common.dto.ApiSuccessResponse
import yourssu.assignment.common.exception.ResponseResult.*
import yourssu.assignment.config.interceptor.Auth
import yourssu.assignment.config.resolver.LoginUserEmail
import javax.validation.Valid

@RestController
class AuthController(private val authService: AuthService) {

    @ApiOperation("회원가입")
    @PostMapping("/auth/signup")
    fun signup(@Valid @RequestBody request: SignupRequest): ResponseEntity<SignupResponse> {
        authService.signup(request).let {
            return ApiSuccessResponse.success(SUCCESS_CREATED_USER, SignupResponse.of(it.email, it.username, it.role))
        }
    }

    @ApiOperation("회원 탈퇴")
    @Auth
    @PostMapping("/auth/resign")
    fun resign(@Valid @RequestBody request: ResignRequest): ResponseEntity<String> {
        authService.resign(request)
        return ApiSuccessResponse.SUCCESS
    }

    @ApiOperation("회원 로그인")
    @PostMapping("/auth/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        authService.login(request).let {
            return ApiSuccessResponse.success(OK_LOGIN, it)
        }
    }

    @ApiOperation("토큰 재발급")
    @Auth
    @PostMapping("/auth/reissue")
    fun login(@LoginUserEmail email: String): ResponseEntity<TokenResponse> {
        authService.reissueToken(email).let {
            return ApiSuccessResponse.success(SUCCESS_REISSUE_TOKEN, it)
        }
    }
}