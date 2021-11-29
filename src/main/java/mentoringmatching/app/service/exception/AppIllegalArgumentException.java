package mentoringmatching.app.service.exception;

public class AppIllegalArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AppIllegalArgumentException(String message) {
        super(message);
    }

}
