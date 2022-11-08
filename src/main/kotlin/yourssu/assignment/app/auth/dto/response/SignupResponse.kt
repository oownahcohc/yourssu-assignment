package yourssu.assignment.app.auth.dto.response

import yourssu.assignment.domain.user.entity.UserRole

data class SignupResponse(
    val email: String,
    val username: String,
    val role: UserRole
) {
    companion object{
        fun of(email: String, username: String, role: UserRole): SignupResponse {
            return SignupResponse(email, username, role)
        }
    }
}