package yourssu.assignment.common.exception.custom

import yourssu.assignment.common.exception.ResponseResult

class InternalServerException : YourSsuException {
    constructor(message: String, responseResult: ResponseResult) : super(message, responseResult) {}
    constructor(message: String) : super(message, ResponseResult.INTERNAL_SERVER_EXCEPTION) {}
}
