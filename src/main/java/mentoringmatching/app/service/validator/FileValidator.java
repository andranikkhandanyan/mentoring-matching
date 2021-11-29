package mentoringmatching.app.service.validator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static mentoringmatching.app.util.FileUtil.*;

@Component
public class FileValidator {
    private static final List<String> ACCEPTED_CONTENT_TYPES =
        Arrays.asList(MIME_TYPE_CSV);

    private static final List<String> ACCEPTED_EXTENSIONS =
        Arrays.asList(EXTENSION_CSV);

    public boolean validateFileType(String contentType, String extension) {
        boolean isContentTypeAcceptable = validateContentType(contentType);
        boolean isExtensionAcceptable = validateExtension(extension);

        return isContentTypeAcceptable && isExtensionAcceptable;
    }

    public boolean validateContentType(String fileContentType) {
        return ACCEPTED_CONTENT_TYPES.contains(fileContentType);
    }

    public boolean validateExtension(String extension) {
        return ACCEPTED_EXTENSIONS.contains(extension);
    }
}
