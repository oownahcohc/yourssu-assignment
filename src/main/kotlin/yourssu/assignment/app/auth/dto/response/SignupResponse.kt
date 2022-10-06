package yourssu.assignment.app.auth.dto.response

data class SignupResponse(
    val email: String,
    val username: String
) {
    companion object{
        fun of(email: String, username: String): SignupResponse {
            return SignupResponse(email, username)
        }
    }
}