package ng.music.loader.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ErrorResponseComposer extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public final ResponseEntity<ErrorBody> ResourceAlreadyExistsExceptionHandler(ResourceAlreadyExistsException e,
                                                                                 WebRequest request) {
        ErrorBody errorDetails = new ErrorBody(e.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorBody> notFoundExceptionHandler(NotFoundException e, WebRequest request) {
        ErrorBody errorDetails = new ErrorBody(e.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileLoadingException.class)
    public final ResponseEntity<ErrorBody> fileLoadingExceptionHandler(FileLoadingException e, WebRequest request) {
        ErrorBody errorDetails = new ErrorBody(e.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFileHeaderException.class)
    public final ResponseEntity<ErrorBody> invalidFileHeaderExceptionHandler(InvalidFileHeaderException e, WebRequest request) {
        ErrorBody errorDetails = new ErrorBody(e.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorBody> defaultExceptionHandler(Exception e, WebRequest request) {
        ErrorBody errorDetails = new ErrorBody(e.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
