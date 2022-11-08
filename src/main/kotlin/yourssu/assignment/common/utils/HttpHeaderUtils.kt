package yourssu.assignment.common.utils

object HttpHeaderUtils {

    const val AUTH_HEADER = "Authorization"
    const val BEARER_TOKEN = "Bearer "

    fun withBearerToken(token: String): String {
        return BEARER_TOKEN + token
    }

}