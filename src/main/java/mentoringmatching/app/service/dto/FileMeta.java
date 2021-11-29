package mentoringmatching.app.service.dto;

import java.io.InputStream;

public class FileMeta {
    private String name;
    private String contentType;
    private String extension;
    private InputStream inputStream;

    public FileMeta() {
    }

    public FileMeta(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getName() {
        return name;
    }

    public FileMeta setName(String name) {
        this.name = name;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public FileMeta setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public FileMeta setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
