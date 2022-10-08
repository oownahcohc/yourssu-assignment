package yourssu.assignment.common.dto

import org.springframework.http.ResponseEntity
import yourssu.assignment.common.exception.ResponseResult

class ApiSuccessResponse {

    companion object{

        val SUCCESS = success(ResponseResult.SUCCESS_OK, ResponseResult.SUCCESS_OK.message)

        fun <T> success(responseResult: ResponseResult, data: T): ResponseEntity<T> {
            return ResponseEntity
                .status(responseResult.statusCode.status)
                .body(data)
        }

    }
}