package nl.uwv.requirementslookup.mappers;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.ScenarioDTO;
import nl.uwv.requirementslookup.entities.Requirement;
import nl.uwv.requirementslookup.entities.Scenario;
import nl.uwv.requirementslookup.entities.Story;
import nl.uwv.requirementslookup.repositories.RequirementRepository;
import nl.uwv.requirementslookup.repositories.StoryRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ScenarioMapper {

    private final RequirementRepository requirementRepository;
    private final StoryRepository storyRepository;

    public ScenarioDTO fromEntity(Scenario scenario){
        ScenarioDTO dto = new ScenarioDTO();
        dto.setId(scenario.getId());
        dto.setName(scenario.getName());
        dto.setDescription(scenario.getDescription());
        if(scenario.getRequirements() != null) {
            dto.setRequirementNames(scenario.getRequirements()
                    .stream()
                    .map(Requirement::getName)
                    .collect(Collectors.toList()));
        }
        Story story = scenario.getStory();
        dto.setStoryName(story.getName());
        dto.setStoryId(story.getId());
        dto.setSuccessfullyTested(scenario.isSuccessfullyTested());
        return dto;
    }

    public Scenario fromDto(ScenarioDTO dto){
        Scenario scenario = new Scenario();
        scenario.setId(dto.getId());
        scenario.setName(dto.getName());
        scenario.setDescription(dto.getDescription());
        if(dto.getRequirementNames() != null) {
            scenario.setRequirements(dto.getRequirementNames()
                    .stream()
                    .map(requirementRepository::findByName)
                    .collect(Collectors.toSet()));
        }
        scenario.setStory(storyRepository.findByName(dto.getStoryName()).orElseThrow());
        scenario.setSuccessfullyTested(dto.isSuccessfullyTested());
        return scenario;
    }
}
