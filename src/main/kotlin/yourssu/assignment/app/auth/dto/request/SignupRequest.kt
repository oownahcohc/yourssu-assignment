package yourssu.assignment.app.auth.dto.request

import yourssu.assignment.app.user.dto.request.CreateUserDto
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignupRequest(
    @field:NotBlank
    @Email
    val email: String,

    @field: NotBlank
    val password: String,

    @field: NotBlank
    val username: String,
) {
    fun toCreateUserDto(encodedPwd: String): CreateUserDto {
        return CreateUserDto.of(username, email, encodedPwd)
    }
}

