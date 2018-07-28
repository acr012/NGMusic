package ng.music.loader.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileLoadingException extends RuntimeException {

    public FileLoadingException(String exception) {
        super(exception);
    }

}
