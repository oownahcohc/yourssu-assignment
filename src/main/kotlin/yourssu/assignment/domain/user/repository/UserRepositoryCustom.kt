package yourssu.assignment.domain.user.repository

import yourssu.assignment.domain.user.entity.User

interface UserRepositoryCustom {
    fun existByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
    fun findByUserId(userId: Long): User?
}