package mentoringmatching.app.controller;

import mentoringmatching.app.matcher.predicate.OperationType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/predicate")
public class PredicateController {

    @GetMapping("/operation-types")
    public List<OperationType> getPredicateOperationTypes() {
        return Arrays.asList(OperationType.values());
    }
}
