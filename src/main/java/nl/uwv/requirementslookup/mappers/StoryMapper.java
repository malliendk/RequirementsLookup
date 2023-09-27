package nl.uwv.requirementslookup.mappers;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.StoryDTO;
import nl.uwv.requirementslookup.entities.Requirement;
import nl.uwv.requirementslookup.entities.Scenario;
import nl.uwv.requirementslookup.entities.Story;
import nl.uwv.requirementslookup.repositories.RequirementRepository;
import nl.uwv.requirementslookup.repositories.ScenarioRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StoryMapper {

    private RequirementRepository requirementRepository;
    private ScenarioRepository scenarioRepository;

    public StoryDTO fromEntity(Story story){
        StoryDTO dto = new StoryDTO();
        dto.setId(story.getId());
        dto.setName(story.getName());
        dto.setDescription(story.getDescription());
        dto.setRequirementNames(story.getRequirements()
                .stream()
                .map(Requirement::getName)
                .collect(Collectors.toList()));
        dto.setScenarioNames(story.getScenarios()
                .stream()
                .map(Scenario::getName)
                .collect(Collectors.toList()));
        dto.setAllRequirementsSuccessfullyTested(story.isAllRequirementsSuccesfullyTested());
        return dto;
    }

    public Story fromDto(StoryDTO storyDTO){
        Story story = new Story();
        story.setId(storyDTO.getId());
        story.setName(storyDTO.getName());
        story.setDescription(storyDTO.getDescription());
        story.setRequirements(storyDTO.getRequirementNames()
                .stream()
                .map(requirement -> requirementRepository.findByName(requirement))
                .collect(Collectors.toList()));
        story.setScenarios(storyDTO.getScenarioNames()
                .stream()
                .map(scenario -> scenarioRepository.findByName(scenario))
                .collect(Collectors.toList()));
        story.setAllRequirementsSuccesfullyTested(storyDTO.isAllRequirementsSuccessfullyTested());
        return story;
    }


}
