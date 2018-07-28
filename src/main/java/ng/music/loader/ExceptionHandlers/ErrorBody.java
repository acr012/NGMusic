package ng.music.loader.ExceptionHandlers;

import java.util.Date;

public class ErrorBody {

    private String message;
    private String details;
    private Date time;

    public ErrorBody(String message, String details, Date time) {
        super();
        this.message = message;
        this.details = details;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() { return details; }
}
