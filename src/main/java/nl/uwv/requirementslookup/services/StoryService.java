package nl.uwv.requirementslookup.services;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.StoryDTO;
import nl.uwv.requirementslookup.entities.Requirement;
import nl.uwv.requirementslookup.entities.Story;
import nl.uwv.requirementslookup.exceptions.RequirementException;
import nl.uwv.requirementslookup.mappers.StoryMapper;
import nl.uwv.requirementslookup.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final StoryMapper mapper;

    public StoryDTO create(StoryDTO storyDTO) {
        Story story = mapper.fromDto(storyDTO);
        Story savedStory = storyRepository.save(story);
        return mapper.fromEntity(savedStory);
    }

    public StoryDTO find(Long id) {
        Story story = storyRepository.findById(id).orElseThrow();
        return mapper.fromEntity(story);
    }

    public StoryDTO findByName(String name) {
        Story story = storyRepository.findByName(name).orElseThrow();
        return mapper.fromEntity(story);
    }

    public List<StoryDTO> all() {
        return storyRepository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<String> allNames(){
        return all().stream()
                .map(StoryDTO::getName)
                .collect(Collectors.toList());
    }

    public StoryDTO update(Long id, StoryDTO storyDTO) {
        if (!id.equals(storyDTO.getId())) {
            throw new RuntimeException("ids don't match");
        }
        Story story = mapper.fromDto(storyDTO);
        Story updateStory = storyRepository.save(story);
        return mapper.fromEntity(updateStory);
    }

    public void delete(Long id) {
        storyRepository.deleteById(id);
    }

    public boolean verifyAllRequirementsTested(Long storyId) throws RequirementException {
        Story story = storyRepository.findById(storyId).orElseThrow();
        for (Requirement requirement : story.getRequirements()) {
            if (!requirement.isSuccessfullyTested()) {
                throw new RequirementException(RequirementException.REQUIREMENTS_NOT_TESTED);
            }
        }
        story.setAllRequirementsSuccesfullyTested(true);
        storyRepository.save(story);
        return true;
    }
}
