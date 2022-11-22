package yourssu.assignment.config.interceptor

import yourssu.assignment.domain.user.entity.UserRole
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Auth(val role: UserRole = UserRole.USER)
