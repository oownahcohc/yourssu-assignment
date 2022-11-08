package yourssu.assignment.config.interceptor

import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import yourssu.assignment.common.exception.custom.UnAuthorizedException
import yourssu.assignment.common.utils.HttpHeaderUtils
import yourssu.assignment.common.utils.JwtUtils
import javax.servlet.http.HttpServletRequest

@Component
class LoginCheckHandler(val jwtProvider: JwtUtils) {

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
}
