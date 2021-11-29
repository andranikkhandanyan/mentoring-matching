package mentoringmatching.app.controller;

import mentoringmatching.app.service.MatcherConfigTemplateService;
import mentoringmatching.app.service.dto.MatcherConfigTemplateDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matcher-config-template")
public class MatcherConfigTemplateController {

    private final MatcherConfigTemplateService matcherConfigTemplateService;

    public MatcherConfigTemplateController(MatcherConfigTemplateService matcherConfigTemplateService) {
        this.matcherConfigTemplateService = matcherConfigTemplateService;
    }

    @GetMapping
    public List<MatcherConfigTemplateDTO> getAll() {
        return matcherConfigTemplateService.getConfigTemplates();
    }
}
