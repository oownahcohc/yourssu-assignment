package yourssu.assignment.common.exception.custom

import yourssu.assignment.common.exception.ResponseResult

class ConflictException(message: String, responseResult: ResponseResult): YourSsuException(message, responseResult) {
}