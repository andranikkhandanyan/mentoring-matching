package mentoringmatching.app.controller.error;

public class Problem {

    private int code;
    // Humane readable error message
    private String message;
    // Technical information
    private String developerMessage;
    // Additional information
    private Object data;

    public Problem(String message) {
        this.message = message;
    }

    public Problem(int code, String message) {
        this(code, message, null, null);
    }

    public Problem(int code, String message, String developerMessage, Object data) {
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public Object getData() {
        return data;
    }

}
