package yourssu.assignment.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import yourssu.assignment.domain.user.entity.User

interface UserRepository: JpaRepository<User, Long>, UserRepositoryCustom {
}