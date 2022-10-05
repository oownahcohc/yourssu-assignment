package yourssu.assignment.domain.article

import yourssu.assignment.domain.comment.Comment
import yourssu.assignment.domain.common.AuditingTimeEntity
import yourssu.assignment.domain.user.User
import java.util.function.Predicate
import javax.persistence.*

@Entity
class Article(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String
): AuditingTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", nullable = false)
    val id: Long? = null

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf()
    val getComments: List<Comment>
        get() = comments.toList()

    companion object {
        fun newInstance(user: User, title: String, content: String): Article {
            return Article(user, title, content)
        }
    }

    fun addComment(comment: Comment) {
        this.comments.add(comment)
    }

    fun updateComment(prevComment: Comment, changedComment: Comment) {
        this.comments.remove(prevComment)
        this.comments.add(changedComment)
    }

    fun updateTitle(title: String) {
        this.title = title
    }

    fun updateContent(content: String) {
        this.content = content
    }

    fun updateTitleAndContent(title: String, content: String) {
        this.title = title
        this.content = content
    }

    fun deleteComment() {
        this.comments.removeAll(getComments)
    }

}