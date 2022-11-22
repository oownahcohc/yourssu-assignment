package yourssu.assignment.config.interceptor

import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import yourssu.assignment.common.constants.JwtConstants
import yourssu.assignment.domain.user.entity.UserRole
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(private val loginCheckHandler: LoginCheckHandler) : HandlerInterceptor {

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        val auth = handler.getMethodAnnotation(Auth::class.java) ?: return true

        when (auth.role) {
            UserRole.ADMIN ->
                if (loginCheckHandler.validateAdminUserRole(request)) {
                    setAttributeUserEmail(request)
                }
            UserRole.USER -> setAttributeUserEmail(request)
        }
        return true
    }

    private fun setAttributeUserEmail(request: HttpServletRequest) {
        val userEmail: String = loginCheckHandler.getUserEmail(request)
        request.setAttribute(JwtConstants.USER_EMAIL, userEmail)
    }
}
