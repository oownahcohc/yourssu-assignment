package yourssu.assignment.config.web

import org.springframework.stereotype.Component
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import yourssu.assignment.config.interceptor.AuthInterceptor
import yourssu.assignment.config.resolver.LoginUserIdResolver

@Component
class WebConfig(
    val authInterceptor: AuthInterceptor,
    val loginUserIdResolver: LoginUserIdResolver
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(loginUserIdResolver)
    }

}