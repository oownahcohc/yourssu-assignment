package yourssu.assignment.common.dto

import com.fasterxml.jackson.annotation.JsonFormat
import yourssu.assignment.domain.common.AuditingTimeEntity
import java.time.LocalDateTime

abstract class AuditingTimeResponse(

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
    private var createdAt: LocalDateTime,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
    private var updatedAt: LocalDateTime,

) {
    protected fun setBaseTime(auditingTimeEntity: AuditingTimeEntity) {
        this.createdAt = auditingTimeEntity.createdAt!!
        this.updatedAt = auditingTimeEntity.updatedAt!!
    }
}