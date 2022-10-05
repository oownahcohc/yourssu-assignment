package yourssu.assignment.common.exception.custom

import yourssu.assignment.common.exception.ResponseResult

abstract class YourSsuException(message: String, val responseResult: ResponseResult) : RuntimeException(message) {
}
