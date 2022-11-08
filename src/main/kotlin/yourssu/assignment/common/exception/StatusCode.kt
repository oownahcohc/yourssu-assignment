package yourssu.assignment.common.exception

enum class StatusCode(val status: Int) {
    /**
     * Success Code
     */
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),

    /**
     * Error Code
     */
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    NOT_ACCEPTABLE(406),
    CONFLICT(409),
    UNSUPPORTED_MEDIA_TYPE(415),
    INTERNAL_SERVER(500),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503)
    ;
}