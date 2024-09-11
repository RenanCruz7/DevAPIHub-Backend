package backend.DevAPIHub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404Error() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handle405Error() {
        return ResponseEntity.status(405).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequestError(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorData::new).toList());

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handle403Error() {
        return ResponseEntity.status(403).build();
    }


    private record ValidationErrorData(String campo, String mensagem) {
        public ValidationErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
