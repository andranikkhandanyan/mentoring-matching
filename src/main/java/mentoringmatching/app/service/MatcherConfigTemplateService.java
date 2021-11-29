package mentoringmatching.app.service;

import mentoringmatching.app.repository.MatcherConfigTemplateRepository;
import mentoringmatching.app.service.dto.MatcherConfigTemplateDTO;
import mentoringmatching.app.service.mapper.MatcherTemplateConfigMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatcherConfigTemplateService {

    private final MatcherConfigTemplateRepository matcherConfigTemplateRepository;
    private final MatcherTemplateConfigMapper matcherTemplateConfigMapper;

    public MatcherConfigTemplateService(MatcherConfigTemplateRepository matcherConfigTemplateRepository, MatcherTemplateConfigMapper matcherTemplateConfigMapper) {
        this.matcherConfigTemplateRepository = matcherConfigTemplateRepository;
        this.matcherTemplateConfigMapper = matcherTemplateConfigMapper;
    }

    public List<MatcherConfigTemplateDTO> getConfigTemplates() {
        return matcherConfigTemplateRepository.findAll()
                .stream()
                .map(matcherTemplateConfigMapper::toDto)
                .collect(Collectors.toList());
    }
}
