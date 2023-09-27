package nl.uwv.requirementslookup.repositories;

import nl.uwv.requirementslookup.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    Optional<Story> findByName(String name);
}
