package yourssu.assignment.app.auth.dto.request

import yourssu.assignment.app.user.dto.request.CreateUserDto
import yourssu.assignment.domain.user.entity.UserRole
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SignupRequest(
    @field:NotBlank
    @Email
    val email: String,

    @field: NotBlank
    val password: String,

    @field: NotBlank
    val username: String,

    @field: NotNull
    val role: UserRole
) {
    fun toCreateUserDto(encodedPwd: String): CreateUserDto {
        return CreateUserDto.of(username, email, encodedPwd, role)
    }
}

