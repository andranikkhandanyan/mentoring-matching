package mentoringmatching.app.controller.error;

import mentoringmatching.app.service.exception.AppIllegalArgumentException;
import mentoringmatching.app.service.exception.AppIllegalStateException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ExceptionTranslator {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Problem handleException(Exception e) {
        e.printStackTrace();
        return new Problem(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AppIllegalStateException.class})
    @ResponseBody
    public Problem handleIllegalStateException(AppIllegalStateException e) {
        return new Problem(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AppIllegalArgumentException.class})
    @ResponseBody
    public Problem handleIllegalArgumentException(AppIllegalArgumentException e) {
        return new Problem(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Problem handleIllegalArgumentException(MethodArgumentNotValidException e) {
        StringBuilder fields = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            fields.append(((FieldError) error).getField()).append(" ");
        });
        return new Problem(HttpStatus.BAD_REQUEST.value(), fields + "validation(s) didn't passed");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({FileSizeLimitExceededException.class, MultipartException.class})
    @ResponseBody
    public Problem handleFileSizeLimitError(Exception e) {
        e.printStackTrace();
        return new Problem(HttpStatus.BAD_REQUEST.value(), "FileSizeLimitExceeded");
    }

}
