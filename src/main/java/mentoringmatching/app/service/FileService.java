package mentoringmatching.app.service;

import mentoringmatching.app.service.dto.FileMeta;
import mentoringmatching.app.service.exception.AppIllegalArgumentException;
import mentoringmatching.app.service.exception.AppIllegalStateException;
import mentoringmatching.app.service.validator.FileValidator;
import mentoringmatching.app.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileValidator fileValidator;

    public FileService(FileValidator fileValidator) {
        this.fileValidator = fileValidator;
    }

    public FileMeta processFile(MultipartFile multipartFile) throws IOException {
        String extension = FileUtil.getFileExtension(multipartFile);
        if (!StringUtils.hasText(extension)) {
            throw new AppIllegalStateException("Unknown file extension");
        }
        String contentType = FileUtil.getFileContentType(multipartFile);

        if (!fileValidator.validateFileType(contentType, extension)) {
            throw new AppIllegalArgumentException("Wrong file type");
        }

        return new FileMeta(multipartFile.getInputStream())
                .setContentType(contentType)
                .setExtension(extension);
    }
}
