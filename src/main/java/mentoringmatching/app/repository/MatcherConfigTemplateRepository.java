package mentoringmatching.app.repository;

import mentoringmatching.app.entity.MatcherConfigTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatcherConfigTemplateRepository extends JpaRepository<MatcherConfigTemplate, Long> {
}
