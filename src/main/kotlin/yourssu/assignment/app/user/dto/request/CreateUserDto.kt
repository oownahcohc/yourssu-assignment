package yourssu.assignment.app.user.dto.request

import yourssu.assignment.domain.user.entity.UserRole

data class CreateUserDto(
    val username: String,
    val email: String,
    val password: String,
    val role: UserRole
) {

    companion object {
        fun of(username: String, email: String, password: String, role: UserRole): CreateUserDto {
            return CreateUserDto(username, email, password, role)
        }
    }

}
