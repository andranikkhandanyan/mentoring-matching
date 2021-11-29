package mentoringmatching.app.matcher;

import mentoringmatching.app.matcher.predicate.OperationType;

import java.io.Serializable;

public class Config implements Serializable {
    private String fieldName;
    private int diff;
    private int score;
    private OperationType operationType;

    public Config(String fieldName, int diff, int score, OperationType operationType) {
        this.fieldName = fieldName;
        this.diff = diff;
        this.score = score;
        this.operationType = operationType;
    }

    public Config() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Config setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }
}
