package yourssu.assignment.common.exception.custom

import yourssu.assignment.common.exception.ResponseResult

class NotFoundException(message: String, responseResult: ResponseResult): YourSsuException(message, responseResult) {
}