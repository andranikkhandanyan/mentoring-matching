package mentoringmatching.app.entity;

import mentoringmatching.app.matcher.predicate.OperationType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "matcher_config_template")
public class MatcherConfigTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matcher_config_template_id_seq")
    @SequenceGenerator(name = "matcher_config_template_id_seq", sequenceName = "matcher_config_template_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "diff")
    private int diff;

    @Column(name = "score")
    private int score;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
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
        MatcherConfigTemplate that = (MatcherConfigTemplate) o;
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
