package yourssu.assignment.config.resolver

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import yourssu.assignment.common.constants.JwtConstants
import yourssu.assignment.common.exception.custom.InternalServerException
import yourssu.assignment.config.interceptor.Auth

@Component
class LoginUserIdResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginUserEmail::class.java) && parameter.parameterType == String::class.java
    }

    @Throws(Exception::class)
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        if (parameter.getMethodAnnotation(Auth::class.java) == null) {
            throw InternalServerException("@Auth 어노테이션이 필요한 컨트롤러입니다")
        }
        return webRequest.getAttribute(JwtConstants.USER_EMAIL, 0)
            ?: throw InternalServerException(String.format("USER_EMAIL 를 가져오지 못했습니다. (%s - %s)",
                parameter.javaClass,
                parameter.method))
    }
}