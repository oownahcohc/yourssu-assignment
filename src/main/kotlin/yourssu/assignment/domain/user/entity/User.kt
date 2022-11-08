package yourssu.assignment.domain.user

import yourssu.assignment.domain.article.Article
import yourssu.assignment.domain.comment.Comment
import yourssu.assignment.domain.common.AuditingTimeEntity
import javax.persistence.*

@Entity
class User(
    @Column(nullable = false) val username: String,
    @Column(nullable = false) val email: String,
    @Column(nullable = false) val password: String,
    @Column(nullable = false) val refreshToken: String,
): AuditingTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val articles: MutableList<Article> = mutableListOf()
    val getArticles: List<Article>
        get() = articles.toList()



    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf()
    val getComments: List<Comment>
        get() = comments.toList()

    companion object { // 정적 팩토리 메소드
        fun newInstance(username: String, email: String, password: String): User {
            return User(username, email, password)
        }
    }

    fun addArticle(article: Article) {
        this.articles.add(article)
    }

    fun addComment(comment: Comment) {
        this.comments.add(comment)
    }

    fun deleteArticleAndComment() {
        this.articles.removeAll(getArticles)
        this.comments.removeAll(getComments)
    }

}