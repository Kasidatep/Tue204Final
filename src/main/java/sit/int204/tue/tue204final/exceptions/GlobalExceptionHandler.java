package sit.int204.tue.tue204final.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String TRACE = "Trace";

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTodoNotFound(TodoNotFoundException todoNotFoundException, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),todoNotFoundException.getMessage(), webRequest.getDescription(false).substring(4));
//        for(StackTraceElement stack:todoNotFoundException.getStackTrace()){
//            errorResponse.addValidationError(stack.getFileName(), stack.getClassName());
//        }
        errorResponse.addValidationError("Type", "TodoNotFoundException");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), webRequest.getDescription(false).substring(4));
        errorResponse.addValidationError("Type", "RuntimeException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception exception, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), webRequest.getDescription(false).substring(4));
        errorResponse.addValidationError("Type", "handleAllException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }



}
