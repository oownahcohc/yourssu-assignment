package yourssu.assignment.app.admin.dto.response

import yourssu.assignment.domain.user.entity.User
import yourssu.assignment.domain.user.entity.UserRole
import java.time.LocalDateTime

data class AdminUserResponse(
    val userId: Long,
    val email: String,
    val username: String,
    val role: UserRole,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(user: User): AdminUserResponse {
            return AdminUserResponse(
                userId = user.id!!,
                email = user.email,
                username = user.username,
                role = user.role,
                createdAt = user.createdAt!!,
                updatedAt = user.updatedAt!!
            )
        }
    }
}