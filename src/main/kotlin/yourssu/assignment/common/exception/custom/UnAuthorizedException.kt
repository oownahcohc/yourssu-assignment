package yourssu.assignment.common.exception.custom

import yourssu.assignment.common.exception.ResponseResult

class UnAuthorizedException: YourSsuException {
    constructor(message: String, responseResult: ResponseResult): super(message, responseResult) {}
    constructor(message: String): super(message, ResponseResult.UNAUTHORIZED_WRONG_PASSWORD_EXCEPTION) {}
}