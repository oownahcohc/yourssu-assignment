package yourssu.assignment.app.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yourssu.assignment.app.user.dto.request.CreateUserDto
import yourssu.assignment.domain.user.User
import yourssu.assignment.domain.user.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun registerUser(request: CreateUserDto): User {
        return userRepository.save(User.newInstance(request.username, request.email, request.password))
    }

}