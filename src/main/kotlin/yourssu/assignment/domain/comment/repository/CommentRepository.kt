package yourssu.assignment.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import yourssu.assignment.domain.comment.Comment

interface CommentRepository: JpaRepository<Comment, Long>, CommentRepositoryCustom {
}