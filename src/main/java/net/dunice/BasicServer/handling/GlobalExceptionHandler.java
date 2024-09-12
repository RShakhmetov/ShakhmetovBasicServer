package net.dunice.BasicServer.handling;

import net.dunice.BasicServer.response.BaseSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseSuccessResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        var e = ex.getBindingResult().getAllErrors();
        Integer err = e.stream()
                .map(item -> ErrorCodes.ERROR_CODE_MAP.get(item.getDefaultMessage()))
                .findFirst()
                .orElse(0);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new BaseSuccessResponse(err));
    }
}
