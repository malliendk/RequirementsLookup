package nl.uwv.requirementslookup.repositories;

import nl.uwv.requirementslookup.entities.Requirement;
import nl.uwv.requirementslookup.entities.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {

    Requirement findByName(String name);

//    @Query("SELECT r FROM Requirement r JOIN r.scenarios s WHERE s.id = :scenarioId")
//    List<Requirement> findAllByScenario(@Param("scenarioId") Long scenarioId);


}
