package yourssu.assignment.domain.comment

import yourssu.assignment.domain.article.Article
import yourssu.assignment.domain.common.AuditingTimeEntity
import yourssu.assignment.domain.user.entity.User
import javax.persistence.*

@Entity
class Comment(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    val article: Article,

    @Column(nullable = false)
    var content: String
): AuditingTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long? = null

    companion object {
        fun newInstance(user: User, article: Article, content: String): Comment {
            return Comment(user, article, content)
        }
    }

    fun updateContent(content: String) {
        this.content = content
    }

}