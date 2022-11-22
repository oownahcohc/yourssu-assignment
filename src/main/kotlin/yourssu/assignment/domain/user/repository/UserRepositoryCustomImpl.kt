package yourssu.assignment.domain.user.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.jetbrains.annotations.Nullable
import yourssu.assignment.domain.user.entity.QUser.*
import yourssu.assignment.domain.user.entity.User
import yourssu.assignment.domain.user.entity.UserRole
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserRepositoryCustomImpl(private val query: JPAQueryFactory): UserRepositoryCustom {

    override fun existByEmail(email: String): Boolean {
        return query
            .selectOne()
            .from(user)
            .where(user.email.eq(email))
            .fetchFirst() != null
    }

    override fun findByEmail(email: String): User? {
        return query
            .selectFrom(user)
            .where(user.email.eq(email))
            .fetchOne()
    }

    override fun findByUserId(userId: Long): User? {
        return query
            .selectFrom(user)
            .where(user.id.eq(userId))
            .fetchOne()
    }

    override fun findUserRoleIsUSERByEmail(email: String): User? {
        return query
            .selectFrom(user)
            .where(
                user.email.eq(email),
                user.role.eq(UserRole.USER),
            )
            .fetchOne()
    }

    override fun findAllUser(createdAtStart: String?, createdAtEnd: String?): List<User> {
        var result: List<User>? = null
        if (createdAtStart == null && createdAtEnd != null) {
            result = findAllUserByBefore(createdAtEnd)
        }
        else if (createdAtStart != null && createdAtEnd == null) {
            result = findAllUserByAfter(createdAtStart)
        }
        else if (createdAtStart != null && createdAtEnd != null) {
            result = findAllUserByBetween(createdAtStart, createdAtEnd)
        }
        return result!!
    }

    private fun findAllUserByBetween(createdAtStart: String, createdAtEnd: String): List<User>? {
        return query
            .selectFrom(user)
            .where(
                user.createdAt.between(
                    convertStringToLocalDate(createdAtStart).atStartOfDay(),
                    convertStringToLocalDate(createdAtEnd).atStartOfDay()
                ),
                user.role.eq(UserRole.USER)
            )
            .orderBy(user.id.desc())
            .fetch()
    }

    private fun findAllUserByAfter(createdAt: String): List<User>? {
        return query
            .selectFrom(user)
            .where(
                user.createdAt.after(convertStringToLocalDate(createdAt).atStartOfDay()),
                user.role.eq(UserRole.USER)
            )
            .orderBy(user.id.desc())
            .fetch()
    }

    private fun findAllUserByBefore(createdAt: String): List<User>? {
        return query
            .selectFrom(user)
            .where(
                user.createdAt.before(convertStringToLocalDate(createdAt).atStartOfDay()),
                user.role.eq(UserRole.USER)
            )
            .orderBy(user.id.desc())
            .fetch()
    }

    private fun convertStringToLocalDate(createdAt: String): LocalDate {
        return LocalDate.parse(createdAt, DateTimeFormatter.ISO_DATE)
    }
}