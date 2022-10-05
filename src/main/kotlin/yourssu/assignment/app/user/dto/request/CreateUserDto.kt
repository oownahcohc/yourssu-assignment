package yourssu.assignment.app.user.dto.request

data class CreateUserDto(
    val username: String,
    val email: String,
    val password: String
) {

    companion object {
        fun of(username: String, email: String, password: String): CreateUserDto {
            return CreateUserDto(username, email, password)
        }
    }

}
