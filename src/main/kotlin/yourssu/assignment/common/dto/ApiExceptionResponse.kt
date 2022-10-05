package yourssu.assignment.common.dto

import com.fasterxml.jackson.annotation.JsonFormat
import yourssu.assignment.common.exception.ResponseResult
import java.time.LocalDateTime
import java.time.ZoneId

data class ApiExceptionResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
    val timestamp: LocalDateTime,
    val status: Int,
    val errorType: String,
    val message: String
) {
    companion object {

        private val now = LocalDateTime.now(ZoneId.of("Asia/Seoul"))

        fun error(result: ResponseResult): ApiExceptionResponse {
            return ApiExceptionResponse(now, result.statusCode.status, result.name, result.message)
        }

        fun error(result: ResponseResult, message: String): ApiExceptionResponse {
            return ApiExceptionResponse(now, result.statusCode.status, result.name, message)
        }

    }
}