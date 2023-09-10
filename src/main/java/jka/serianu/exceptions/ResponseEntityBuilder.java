package jka.serianu.exceptions;

import jka.serianu.exceptions.model.ApiError;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    private ResponseEntityBuilder(){}
    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}
