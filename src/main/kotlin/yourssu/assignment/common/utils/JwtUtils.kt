package yourssu.assignment.common.utils

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import yourssu.assignment.app.auth.dto.response.TokenResponse
import yourssu.assignment.common.constants.JwtConstants
import yourssu.assignment.common.log.logger
import yourssu.assignment.domain.user.entity.UserRole
import java.security.Key
import java.util.*

@Component
class JwtUtils(@Value("\${jwt.secret}") secretKey: String?) {

    private val ACCESS_TOKEN_EXPIRES_TIME = 14 * 24 * 60 * 60 * 1000L // 30분 -> 개발시 14일
    private val REFRESH_TOKEN_EXPIRES_TIME = 30 * 24 * 60 * 60 * 1000L // 7일  -> 개발시 30일

    private var secretKey: Key? = null

    fun createTokenByUserEmailAndUserRole(email: String, role: UserRole): TokenResponse {
        val now = Date().time
        val accessTokenExpiresIn = Date(now + ACCESS_TOKEN_EXPIRES_TIME)
        val refreshTokenExpiresIn = Date(now + REFRESH_TOKEN_EXPIRES_TIME)

        val userClaimsMap: MutableMap<String, Any> = mutableMapOf()
        userClaimsMap[JwtConstants.USER_EMAIL] = email
        userClaimsMap[JwtConstants.USER_ROLE] = role

        // accessToken 생성
        val accessToken = Jwts.builder()
            .addClaims(userClaimsMap)
            .setExpiration(accessTokenExpiresIn)
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact()
        val refreshToken = Jwts.builder()
            .setExpiration(refreshTokenExpiresIn)
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact()
        return TokenResponse.of(accessToken, refreshToken)
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            logger().info("Invalid JWT Token", e)
        } catch (e: MalformedJwtException) {
            logger().info("Invalid JWT Token", e)
        } catch (e: ExpiredJwtException) {
            logger().info("Expired JWT Token", e)
        } catch (e: UnsupportedJwtException) {
            logger().info("Unsupported JWT Token", e)
        } catch (e: IllegalArgumentException) {
            logger().info("JWT claims string is empty", e)
        }
        return false
    }

    fun getUserEmailFromJwt(accessToken: String): String? {
        return parseClaims(accessToken).get(JwtConstants.USER_EMAIL, String::class.java)
    }

    private fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(accessToken)
                .body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }

    init {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        this.secretKey = Keys.hmacShaKeyFor(keyBytes)
    }

}