package akhandanyan.csv;

public class Options {
    public static final String DELIMITER_COMMA = ",";
    public static final String DELIMITER_SEMICOLON = ";";

    private boolean isFirstRowHeader;
    private String delimiter;
    private String[] columns;

    public boolean isFirstRowHeader() {
        return isFirstRowHeader;
    }

    public Options setFirstRowHeader(boolean firstRowHeader) {
        isFirstRowHeader = firstRowHeader;
        return this;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public Options setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public String[] getColumns() {
        return columns;
    }

    public Options setColumns(String[] columns) {
        this.columns = columns;
        return this;
    }
}
