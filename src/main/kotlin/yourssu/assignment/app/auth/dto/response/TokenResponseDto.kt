package yourssu.assignment.app.auth.dto.response

class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String
) {

    companion object {
        fun of(accessToken: String, refreshToken: String): TokenResponseDto {
            return TokenResponseDto(accessToken, refreshToken)
        }
    }
}