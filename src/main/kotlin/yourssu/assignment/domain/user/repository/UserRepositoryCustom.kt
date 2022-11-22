package yourssu.assignment.domain.user.repository

import org.jetbrains.annotations.Nullable
import yourssu.assignment.domain.user.entity.User

interface UserRepositoryCustom {
    fun existByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
    fun findByUserId(userId: Long): User?
    fun findUserRoleIsUSERByEmail(email: String): User?
    fun findAllUser(createdAtStart: String?, createdAtEnd: String?): List<User>
}