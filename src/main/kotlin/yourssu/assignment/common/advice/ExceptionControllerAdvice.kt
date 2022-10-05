package yourssu.assignment.common.advice

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpStatus.*
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpMediaTypeException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingRequestValueException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import yourssu.assignment.common.dto.ApiExceptionResponse
import yourssu.assignment.common.exception.ResponseResult.*
import yourssu.assignment.common.exception.custom.YourSsuException
import yourssu.assignment.common.log.logger
import java.util.*
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ExceptionControllerAdvice {

    /**
     * <b>YourSsu Custom Exception</b>
     */
    @ExceptionHandler(YourSsuException::class)
    protected fun handleCustomException(exception: YourSsuException): ResponseEntity<ApiExceptionResponse> {
        logger().error(exception.message, exception)
        return ResponseEntity
            .status(exception.responseResult.statusCode.status)
            .body(ApiExceptionResponse.error(exception.responseResult))
    }

    /**
     * <b>400 Bad Request</b><br>
     * Spring Validation
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBadRequest(e: BindException): ApiExceptionResponse {
        logger().error(e.message)
        val fieldError = Objects.requireNonNull(e.fieldError)
        return ApiExceptionResponse.error(VALIDATION_EXCEPTION, String.format("%s (%s)", fieldError?.defaultMessage, fieldError?.field))
    }

    /**
     * <b>400 Bad Request</b><br>
     * 잘못된 Enum 값이 입력된 경우 발생하는 Exception
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ApiExceptionResponse {
        logger().error(e.message)
        return ApiExceptionResponse.error(VALIDATION_ENUM_VALUE_EXCEPTION)
    }

    /**
     * <b>400 Bad Request</b><br>
     * RequestParam, RequestPath, RequestPart 등의 필드가 입력되지 않은 경우 발생하는 Exception
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MissingRequestValueException::class)
    protected fun handle(e: MissingRequestValueException): ApiExceptionResponse {
        logger().error(e.message)
        return ApiExceptionResponse.error(VALIDATION_REQUEST_MISSING_EXCEPTION)
    }


    /**
     * <b>400 Bad Request</b><br>
     * 잘못된 타입이 입력된 경우 발생하는 Exception
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException::class)
    protected fun handleTypeMismatchException(e: TypeMismatchException): ApiExceptionResponse {
        logger().error(e.message)
        return ApiExceptionResponse.error(VALIDATION_WRONG_TYPE_EXCEPTION, String.format("%s (%s)", VALIDATION_WRONG_TYPE_EXCEPTION.message, e.value))
    }

    /**
     * <b>400 Bad Request</b>
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(
        InvalidFormatException::class,
        ServletRequestBindingException::class,
        ConstraintViolationException::class
    )
    protected fun handleInvalidFormatException(e: Exception): ApiExceptionResponse {
        logger().error(e.message)
        return ApiExceptionResponse.error(VALIDATION_EXCEPTION)
    }


    /**
     * <b>405 Method Not Allowed</b><br>
     * 지원하지 않은 HTTP method 호출 할 경우 발생하는 Exception
     */
    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): ApiExceptionResponse {
        logger().error(e.message)
        return ApiExceptionResponse.error(METHOD_NOT_ALLOWED_EXCEPTION)
    }

    /**
     * <b>406 Not Acceptable</b>
     */
    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException::class)
    protected fun handleHttpMediaTypeNotAcceptableException(e: HttpMediaTypeNotAcceptableException): ApiExceptionResponse {
        logger().error(e.message)
        return ApiExceptionResponse.error(NOT_ACCEPTABLE_EXCEPTION)
    }

    /**
     * <b>415 UnSupported Media Type</b> <br>
     * 지원하지 않는 미디어 타입인 경우 발생하는 Exception
     */
    @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeException::class)
    protected fun handleHttpMediaTypeException(e: HttpMediaTypeException): ApiExceptionResponse {
        logger().error(e.message, e)
        return ApiExceptionResponse.error(UNSUPPORTED_MEDIA_TYPE_EXCEPTION)
    }

    /**
     * <b>500 Internal Server</b>
     */
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    protected fun handleException(exception: Exception): ApiExceptionResponse {
        logger().error(exception.message, exception)
        return ApiExceptionResponse.error(INTERNAL_SERVER_EXCEPTION)
    }

}