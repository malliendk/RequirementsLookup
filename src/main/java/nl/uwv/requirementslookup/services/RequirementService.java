package nl.uwv.requirementslookup.services;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.RequirementDTO;
import nl.uwv.requirementslookup.entities.Requirement;
import nl.uwv.requirementslookup.entities.Scenario;
import nl.uwv.requirementslookup.entities.Story;
import nl.uwv.requirementslookup.mappers.RequirementMapper;
import nl.uwv.requirementslookup.mappers.ScenarioMapper;
import nl.uwv.requirementslookup.repositories.RequirementRepository;
import nl.uwv.requirementslookup.repositories.ScenarioRepository;
import nl.uwv.requirementslookup.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequirementService {

    private final RequirementMapper mapper;
    private final RequirementRepository requirementRepository;
    private final ScenarioRepository scenarioRepository;
    private final StoryRepository storyRepository;


    public RequirementDTO create(RequirementDTO requirementDTO){
        Requirement requirement = mapper.fromDto(requirementDTO);
        Requirement savedRequirement = requirementRepository.save(requirement);
        return mapper.fromEntity(savedRequirement);
    }

    public RequirementDTO find(Long id){
        final Requirement requirement = requirementRepository.findById(id).orElseThrow();
        return mapper.fromEntity(requirement);
    }

    public List<RequirementDTO> all(){
        return requirementRepository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<RequirementDTO> findAllByScenario(Long id){
        Scenario scenario = scenarioRepository.findById(id).orElseThrow();
        return scenario.getRequirements()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public SortedSet<String> findAllNamesByScenario(Long id){
        return findAllByScenario(id)
                .stream()
                .map(RequirementDTO::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public List<RequirementDTO> findAllByStory(Long id){
        Story story = storyRepository.findById(id).orElseThrow();
        return story.getRequirements()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public SortedSet<String> findAllNamesByStory(Long id){
        return findAllByStory(id)
                .stream()
                .map(RequirementDTO::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public RequirementDTO update(RequirementDTO requirementDTO, Long id){
        if(!id.equals(requirementDTO.getId())){
            throw new RuntimeException("ids don't match");
        }
        Requirement incomingRequirement = mapper.fromDto(requirementDTO);
        Requirement updatedRequirement = requirementRepository.save(incomingRequirement);
        return mapper.fromEntity(updatedRequirement);
    }

    public void delete(Long id){
        Requirement requirement = requirementRepository.findById(id).orElseThrow();

        Story story = storyRepository.findByName(requirement.getStory().getName()).orElseThrow();
        story.getRequirements().remove(requirement);
        storyRepository.save(story);

        Set<Scenario> scenarios = requirement.getScenarios();
        scenarios.forEach(scenario -> {
            scenario.getRequirements().remove(requirement);
            scenarioRepository.save(scenario);
        });

        requirementRepository.deleteById(id);
    }
}
