package nl.uwv.requirementslookup.repositories;

import nl.uwv.requirementslookup.entities.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
    Scenario findByName(String name);
}
