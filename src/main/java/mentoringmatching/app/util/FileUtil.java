package mentoringmatching.app.util;

import org.springframework.web.multipart.MultipartFile;

public final class FileUtil {

    public static final String MIME_TYPE_CSV = "text/csv";

    public static final String EXTENSION_CSV = "csv";

    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName == null ? null : fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getFilenameWithExtension(String filename, String extension) {
        return filename + "." + extension;
    }

    public static String getFileContentType(MultipartFile file) {
        return file.getContentType();
    }

    private FileUtil() {

    }
}
