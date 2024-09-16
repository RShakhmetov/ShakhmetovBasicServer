package net.dunice.BasicServer.handling;

import jakarta.validation.ConstraintViolationException;
import net.dunice.BasicServer.response.BaseSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseSuccessResponse>
        handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var e = ex.getBindingResult().getAllErrors();
        Integer err = e
                .stream()
                .map(item -> ErrorCodes.ERROR_CODE_MAP
                .get(item.getDefaultMessage()))
                .findFirst()
                .orElse(0);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseSuccessResponse(err));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseSuccessResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        var e = ex.getConstraintViolations();
        List<Integer> err = e.stream().map(item -> ErrorCodes.ERROR_CODE_MAP.get(item.getMessage())).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseSuccessResponse(err.getFirst(), err));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseSuccessResponse>
        handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Integer err = ErrorCodes.ERROR_CODE_MAP.get(ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseSuccessResponse(err));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<BaseSuccessResponse> handleNoSuchElementException(NoSuchElementException ex) {
        Integer err = ErrorCodes.ERROR_CODE_MAP.get(ErrorCodes.TASK_NOT_FOUND.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseSuccessResponse(err));
    }
}
