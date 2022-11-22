package yourssu.assignment.config.interceptor

import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import yourssu.assignment.common.exception.custom.ForbiddenException
import yourssu.assignment.common.exception.custom.UnAuthorizedException
import yourssu.assignment.common.utils.HttpHeaderUtils
import yourssu.assignment.common.utils.JwtUtils
import yourssu.assignment.domain.user.entity.UserRole
import javax.servlet.http.HttpServletRequest

@Component
class LoginCheckHandler(private val jwtProvider: JwtUtils) {

    fun getUserEmail(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(HttpHeaderUtils.AUTH_HEADER)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HttpHeaderUtils.BEARER_TOKEN)) {
            val accessToken: String = bearerToken.substring(HttpHeaderUtils.BEARER_TOKEN.length)
            if (jwtProvider.validateToken(accessToken)) {
                val userEmail: String? = jwtProvider.getUserEmailFromJwt(accessToken)
                if (userEmail != null) {
                    return userEmail
                }
            }
        }
        throw UnAuthorizedException(String.format("잘못된 JWT (%s) 입니다", bearerToken))
    }

    fun validateAdminUserRole(request: HttpServletRequest): Boolean {
        val bearerToken = request.getHeader(HttpHeaderUtils.AUTH_HEADER)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HttpHeaderUtils.BEARER_TOKEN)) {
            val accessToken: String = bearerToken.substring(HttpHeaderUtils.BEARER_TOKEN.length)
            if (jwtProvider.validateToken(accessToken)) {
                val userRole = jwtProvider.getUserRoleFromJwt(accessToken)
                if (userRole == UserRole.ADMIN.toString()) {
                    return true
                }
            }
        }
        throw ForbiddenException(String.format("허용되지 않은 접근 권한 (%s) 입니다", bearerToken))
    }
}
