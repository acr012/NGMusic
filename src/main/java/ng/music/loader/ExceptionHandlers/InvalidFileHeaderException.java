package ng.music.loader.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFileHeaderException extends RuntimeException {

    //TODO: Add detailed explanation of expected header format
    public InvalidFileHeaderException(String exception) {
        super(exception);
    }

}
