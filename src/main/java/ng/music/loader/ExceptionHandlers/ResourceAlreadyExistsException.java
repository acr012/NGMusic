package ng.music.loader.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.FOUND)
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String exception) {
        super(exception);
    }

}