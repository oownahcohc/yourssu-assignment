package yourssu.assignment.app.admin

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yourssu.assignment.app.admin.dto.response.AdminUserResponse
import yourssu.assignment.app.user.service.UserServiceUtils
import yourssu.assignment.domain.user.repository.UserRepository

@Service
class AdminService(private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun retrieveUserByEmail(email: String): AdminUserResponse {
        return UserServiceUtils.findUserRoleIsUserByEmail(userRepository, email).let {
            AdminUserResponse.from(it)
        }
    }

    @Transactional(readOnly = true)
    fun retrieveUser(createdAtStart: String?, createdAtEnd: String?): List<AdminUserResponse> {
        return userRepository.findAllUser(createdAtStart, createdAtEnd).map {
            AdminUserResponse.from(it)
        }.toList()
    }
}