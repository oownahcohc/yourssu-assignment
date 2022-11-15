package yourssu.assignment.common.exception

import yourssu.assignment.common.exception.StatusCode.*

enum class ResponseResult(
    val statusCode: StatusCode,
    val message: String
) {
    // 200 OK
    SUCCESS_OK(OK, "요청 성공"),
    OK_LOGIN(OK, "로그인에 성공했습니다"),

    // 201 CREATED
    SUCCESS_CREATED(CREATED, ""),
    SUCCESS_CREATED_USER(CREATED, "회원가입이 완료되었습니다"),
    SUCCESS_CREATED_ARTICLE(CREATED, "게시글이 작성되었습니다"),
    SUCCESS_UPDATE_ARTICLE(CREATED, "게시글이 수정되었습니다"),
    SUCCESS_CREATE_COMMENT(CREATED, "댓글이 작성되었습니다"),
    SUCCESS_UPDATE_COMMENT(CREATED, "댓글이 수정되었습니다"),
    SUCCESS_REISSUE_TOKEN(CREATED, "토큰이 성공적으로 재발급되었습니다"),

    // 202 ACCEPTED
    SUCCESS_ACCEPTED(ACCEPTED, ""),

    // 204 NOT_CONTENT
    SUCCESS_NO_CONTENT(NO_CONTENT, ""),

    // 400 BAD_REQUEST
    VALIDATION_AUTH_TOKEN_EXCEPTION(BAD_REQUEST, "만료되거나 유효하지 않은 인증 토큰입니다"),
    VALIDATION_EXCEPTION(BAD_REQUEST, "잘못된 요청입니다"),
    VALIDATION_ENUM_VALUE_EXCEPTION(BAD_REQUEST, "잘못된 Enum 값 입니다"),
    VALIDATION_REQUEST_MISSING_EXCEPTION(BAD_REQUEST, "필수적인 요청 값이 입력되지 않았습니다"),
    VALIDATION_WRONG_TYPE_EXCEPTION(BAD_REQUEST, "잘못된 타입이 입력되었습니다."),
    VALIDATION_SOCIAL_TYPE_EXCEPTION(BAD_REQUEST, "잘못된 소셜 프로바이더 입니다."),

    // 401 UNAUTHORIZED
    UNAUTHORIZED_EXCEPTION(UNAUTHORIZED, "토큰이 만료되었습니다.\n다시 로그인 해주세요"),
    UNAUTHORIZED_WRONG_PASSWORD_EXCEPTION(UNAUTHORIZED, "비밀번호가 일치하지 않습니다"),

    // 403 FORBIDDEN
    FORBIDDEN_EXCEPTION(FORBIDDEN, "허용하지 않는 요청입니다."),
    FORBIDDEN_FILE_TYPE_EXCEPTION(FORBIDDEN, "허용되지 않은 파일 형식입니다"),
    FORBIDDEN_FILE_NAME_EXCEPTION(FORBIDDEN, "허용되지 않은 파일 이름입니다"),

    // 404 NOT_FOUND
    NOT_FOUND_EXCEPTION(NOT_FOUND, "존재하지 않습니다"),
    NOT_FOUND_ARTICLE_EXCEPTION(NOT_FOUND, "존재하지 않는 게시글입니다"),
    NOT_FOUND_COMMENT_EXCEPTION(NOT_FOUND, "존재하지 않는 댓글입니다"),
    NOT_FOUND_USER_EXCEPTION(NOT_FOUND, "탈퇴하거나 존재하지 않는 유저입니다"),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED_EXCEPTION(METHOD_NOT_ALLOWED, "지원하지 않는 메소드 입니다"),

    // 406 Not Acceptable
    NOT_ACCEPTABLE_EXCEPTION(NOT_ACCEPTABLE, "Not Acceptable"),

    // 409 CONFLICT
    CONFLICT_EXCEPTION(CONFLICT, "이미 존재합니다"),
    CONFLICT_ALREADY_EXIST_USER_EXCEPTION(CONFLICT, "이미 존재하는 유저입니다"),

    // 415 Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE_EXCEPTION(UNSUPPORTED_MEDIA_TYPE, "해당하는 미디어 타입을 지원하지 않습니다."),

    // 500 INTERNAL_SERVER
    INTERNAL_SERVER_EXCEPTION(INTERNAL_SERVER, "예상치 못한 서버 에러가 발생하였습니다."),

    // 502 BAD_GATEWAY
    BAD_GATEWAY_EXCEPTION(BAD_GATEWAY, "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요"),

    // 503 Service UnAvailable
    SERVICE_UNAVAILABLE_EXCEPTION(SERVICE_UNAVAILABLE, "현재 점검 중입니다.\n잠시 후 다시 시도해주세요"),
    ;
}