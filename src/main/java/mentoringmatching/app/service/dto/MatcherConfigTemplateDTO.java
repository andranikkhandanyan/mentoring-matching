package mentoringmatching.app.service.dto;

import mentoringmatching.app.matcher.predicate.OperationType;

import java.io.Serializable;
import java.util.Objects;

public class MatcherConfigTemplateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String fieldName;
    private int diff;
    private int score;
    private OperationType operationType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatcherConfigTemplateDTO that = (MatcherConfigTemplateDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MatcherConfigTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", diff=" + diff +
                ", score=" + score +
                ", operationType=" + operationType +
                '}';
    }
}
