package mentoringmatching.app.service.mapper;

import mentoringmatching.app.entity.MatcherConfigTemplate;
import mentoringmatching.app.service.dto.MatcherConfigTemplateDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatcherTemplateConfigMapper {

    public List<MatcherConfigTemplateDTO> toDto(List<MatcherConfigTemplate> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }

        List<MatcherConfigTemplateDTO> rv = new ArrayList<>();

        for (MatcherConfigTemplate entity : entities) {
            rv.add(toDto(entity));
        }

        return rv;
    }

    public MatcherConfigTemplateDTO toDto(MatcherConfigTemplate entity) {
        if (entity == null) {
            return null;
        }

        MatcherConfigTemplateDTO rv = new MatcherConfigTemplateDTO();
        rv.setId(entity.getId());
        rv.setName(entity.getName());
        rv.setFieldName(entity.getFieldName());
        rv.setDiff(entity.getDiff());
        rv.setScore(entity.getScore());
        rv.setOperationType(entity.getOperationType());

        return rv;
    }
}
