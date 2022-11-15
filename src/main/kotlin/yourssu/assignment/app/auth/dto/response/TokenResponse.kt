package yourssu.assignment.app.auth.dto.response

import yourssu.assignment.common.utils.HttpHeaderUtils

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(accessToken: String, refreshToken: String): TokenResponse {
            return TokenResponse(HttpHeaderUtils.withBearerToken(accessToken), HttpHeaderUtils.withBearerToken(refreshToken))
        }
    }
}