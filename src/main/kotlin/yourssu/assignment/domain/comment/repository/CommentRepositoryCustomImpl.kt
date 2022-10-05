package yourssu.assignment.domain.comment.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import yourssu.assignment.domain.comment.Comment
import yourssu.assignment.domain.comment.QComment.*

class CommentRepositoryCustomImpl(
    private val query: JPAQueryFactory
): CommentRepositoryCustom {

    override fun findByCommentIdAndArticleId(commentId: Long, articleId: Long): Comment? {
        return query
            .selectFrom(comment)
            .where(
                comment.id.eq(commentId),
                comment.article.id.eq(articleId)
            ).fetchOne()
    }

}