package nl.uwv.requirementslookup.services;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.ScenarioDTO;
import nl.uwv.requirementslookup.entities.Scenario;
import nl.uwv.requirementslookup.entities.Story;
import nl.uwv.requirementslookup.mappers.ScenarioMapper;
import nl.uwv.requirementslookup.repositories.RequirementRepository;
import nl.uwv.requirementslookup.repositories.ScenarioRepository;
import nl.uwv.requirementslookup.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final StoryRepository storyRepository;

    final private RequirementRepository requirementRepository;
    private final ScenarioMapper mapper;

    public ScenarioDTO create(ScenarioDTO scenarioDTO){
        Scenario scenario = mapper.fromDto(scenarioDTO);
        final Scenario savedScenario = scenarioRepository.save(scenario);
        return mapper.fromEntity(scenario);

    }

    public ScenarioDTO find(Long id){
        Scenario scenario = scenarioRepository.findById(id).orElseThrow();
        return mapper.fromEntity(scenario);
    }


    public List<ScenarioDTO> all(){
        return scenarioRepository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ScenarioDTO> findAllByStory(Long id){
        Story story = storyRepository.findById(id).orElseThrow();
        return story.getScenarios()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public ScenarioDTO update(ScenarioDTO scenarioDTO, Long id){
        if(!id.equals(scenarioDTO.getId())){
            throw new RuntimeException("ids don't match");
        }
        Scenario scenario = mapper.fromDto(scenarioDTO);
        Scenario savedScenario = scenarioRepository.save(scenario);
        return mapper.fromEntity(savedScenario);
    }

    public void delete(Long id){
        scenarioRepository.deleteById(id);
    }

    public ScenarioDTO setRequirementsTestedToTrue(ScenarioDTO scenarioDTO, Long id){
        if (!id.equals(scenarioDTO.getId())){
            throw new RuntimeException("ids don't match");
        }

        Scenario scenario = mapper.fromDto(scenarioDTO);
        scenario.setSuccessfullyTested(true);
        scenario.getRequirements().forEach(requirement -> {
            requirement.setSuccessfullyTested(true);
            requirementRepository.save(requirement);
        });
        Scenario updatedScenario = scenarioRepository.save(scenario);
        return mapper.fromEntity(updatedScenario);
    }
}
