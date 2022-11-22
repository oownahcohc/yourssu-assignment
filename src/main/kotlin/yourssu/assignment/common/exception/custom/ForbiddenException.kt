package yourssu.assignment.common.exception.custom

import yourssu.assignment.common.exception.ResponseResult

class ForbiddenException: YourSsuException {
    constructor(message: String, responseResult: ResponseResult) : super(message, responseResult) {}
    constructor(message: String) : super(message, ResponseResult.FORBIDDEN_USER_ROLE_EXCEPTION) {}
}