package yourssu.assignment.app.auth.dto.response

import yourssu.assignment.common.utils.HttpHeaderUtils
import yourssu.assignment.domain.user.entity.UserRole

data class LoginResponse(
    val email: String,
    val username: String,
    val role: UserRole,
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(email: String, username: String, role: UserRole, accessToken: String, refreshToken: String): LoginResponse {
            return LoginResponse(email, username, role, accessToken, refreshToken)
        }
    }
}