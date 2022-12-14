package yourssu.assignment.app.auth

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yourssu.assignment.app.auth.dto.request.LoginRequest
import yourssu.assignment.app.auth.dto.request.ResignRequest
import yourssu.assignment.app.auth.dto.request.SignupRequest
import yourssu.assignment.app.auth.dto.response.LoginResponse
import yourssu.assignment.app.auth.dto.response.TokenResponse
import yourssu.assignment.app.user.service.UserService
import yourssu.assignment.app.user.service.UserServiceUtils
import yourssu.assignment.common.exception.custom.UnAuthorizedException
import yourssu.assignment.common.utils.BCryptUtils
import yourssu.assignment.common.utils.JwtUtils
import yourssu.assignment.domain.user.entity.User
import yourssu.assignment.domain.user.repository.UserRepository

@Service
class AuthService(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val bCryptUtils: BCryptUtils,
    private val jwtUtils: JwtUtils
) {

    @Transactional
    fun signup(request: SignupRequest): User {
        UserServiceUtils.validateNotExistUser(userRepository, request.email)
        return userService.registerUser(request.toCreateUserDto(bCryptUtils.encodePassword(request.password)))
    }

    @Transactional
    fun login(request: LoginRequest): LoginResponse {
        getValidUser(request.email, request.password).let {
            val tokenResponseDto = jwtUtils.createTokenByUserEmailAndUserRole(it.email, it.role)
            it.refreshToken = tokenResponseDto.refreshToken
            return LoginResponse.of(it.email, it.username, it.role, tokenResponseDto.accessToken, tokenResponseDto.refreshToken)
        }
    }

    @Transactional
    fun reissueToken(email: String): TokenResponse {
        val user = UserServiceUtils.findUserByEmail(userRepository, email)
        val reissueTokenInfo = jwtUtils.createTokenByUserEmailAndUserRole(email, user.role)
        user.refreshToken = reissueTokenInfo.refreshToken
        return reissueTokenInfo
    }

    @Transactional
    fun resign(request: ResignRequest) {
        with(getValidUser(request.email, request.password)) {
            this.deleteArticleAndComment()
            userRepository.delete(this)
        }
    }

    @Transactional
    fun getValidUser(email: String, password: String): User {
        val user = UserServiceUtils.findUserByEmail(userRepository, email)
        if (!bCryptUtils.isMatchedPassword(password, user.password)) {
            throw UnAuthorizedException("??????????????? ???????????? ????????????")
        }
        return user
    }

}