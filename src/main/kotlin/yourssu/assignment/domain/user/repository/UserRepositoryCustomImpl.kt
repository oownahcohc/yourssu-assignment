package yourssu.assignment.domain.user.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import yourssu.assignment.domain.user.entity.QUser.*
import yourssu.assignment.domain.user.entity.User

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

}