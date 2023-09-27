package nl.uwv.requirementslookup.mappers;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.RequirementDTO;
import nl.uwv.requirementslookup.entities.Requirement;
import nl.uwv.requirementslookup.entities.Scenario;
import nl.uwv.requirementslookup.entities.Story;
import nl.uwv.requirementslookup.repositories.ScenarioRepository;
import nl.uwv.requirementslookup.repositories.StoryRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RequirementMapper {

    private final ScenarioRepository scenarioRepository;
    private final StoryRepository storyRepository;

    public RequirementDTO fromEntity(Requirement requirement){
        RequirementDTO dto = new RequirementDTO();
        dto.setId(requirement.getId());
        dto.setName(requirement.getName());
        dto.setDescription(requirement.getDescription());
        dto.setSuccessfullyTested(requirement.isSuccessfullyTested());
        dto.setStoryName(requirement.getStory().getName());
        dto.setBuilt(requirement.isBuilt());
        return dto;
    }

    public Requirement fromDto(RequirementDTO dto){
        Requirement requirement = new Requirement();
        requirement.setId(dto.getId());
        requirement.setName(dto.getName());
        requirement.setDescription(dto.getDescription());
        requirement.setSuccessfullyTested(dto.isSuccessfullyTested());
        requirement.setBuilt(dto.isBuilt());
        requirement.setStory(storyRepository.findByName(dto.getStoryName()).orElseThrow());
        return requirement;
    }

}
