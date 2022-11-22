package yourssu.assignment.app.user.service

import org.jetbrains.annotations.NotNull
import yourssu.assignment.common.exception.ResponseResult
import yourssu.assignment.common.exception.custom.ConflictException
import yourssu.assignment.common.exception.custom.NotFoundException
import yourssu.assignment.domain.user.entity.User
import yourssu.assignment.domain.user.repository.UserRepository

class UserServiceUtils {

    companion object {

        fun validateNotExistUser(userRepository: UserRepository, email: String) {
            if (userRepository.existByEmail(email)) {
                throw ConflictException(
                    String.format("이미 존재하는 유저 (%s) 입니다", email),
                    ResponseResult.CONFLICT_ALREADY_EXIST_USER_EXCEPTION)
            }
        }

        @NotNull
        fun findUserByEmail(userRepository: UserRepository, email: String): User {
            return userRepository.findByEmail(email)
                ?: throw NotFoundException(
                    String.format("존재하지 않는 유저 (%s) 입니다", email),
                    ResponseResult.CONFLICT_ALREADY_EXIST_USER_EXCEPTION
                )
        }

        @NotNull
        fun findUserRoleIsUserByEmail(userRepository: UserRepository, email: String): User {
            return userRepository.findUserRoleIsUSERByEmail(email)
                ?: throw NotFoundException(
                    String.format("존재하지 않는 유저 (%s) 입니다", email),
                    ResponseResult.CONFLICT_ALREADY_EXIST_USER_EXCEPTION
                )
        }
    }
}