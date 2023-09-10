package jka.serianu.exceptions;

import jka.serianu.exceptions.model.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ChannelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleBillNotFoundException(ChannelNotFoundException exception) {
        ApiError err = new ApiError(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                exception.getMessage(),
                HttpStatus.NOT_FOUND);

        return ResponseEntityBuilder.build(err);
    }
}
