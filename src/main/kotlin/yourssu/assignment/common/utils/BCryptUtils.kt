package yourssu.assignment.common.utils

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class BCryptUtils(private val passwordEncoder: PasswordEncoder) {

    fun encodePassword(password: String): String {
        return passwordEncoder.encode(password)
    }

    fun isMatchedPassword(reqPwd: String, userPwd: String): Boolean {
        return passwordEncoder.matches(reqPwd, userPwd)
    }

}