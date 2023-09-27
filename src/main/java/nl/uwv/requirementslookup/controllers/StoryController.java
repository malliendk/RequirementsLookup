package nl.uwv.requirementslookup.controllers;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.StoryDTO;
import nl.uwv.requirementslookup.exceptions.RequirementException;
import nl.uwv.requirementslookup.services.StoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/stories")
@AllArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @PostMapping
    public StoryDTO create(@RequestBody StoryDTO storyDTO) {
        return storyService.create(storyDTO);
    }

    @GetMapping("{id}")
    public StoryDTO find(@PathVariable Long id) {
        return storyService.find(id);
    }

    @GetMapping("name/{name}")
    public StoryDTO findByName(@PathVariable String name){
        return storyService.findByName(name);
    }

    @GetMapping("all")
    public List<StoryDTO> all() {
        return storyService.all();
    }

    @GetMapping("names/all")
    public List<String> allNames(){
        return storyService.allNames();
    }

    @PutMapping("{id}")
    public StoryDTO update(@PathVariable Long id, @RequestBody StoryDTO storyDTO){
        return storyService.update(id, storyDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        storyService.delete(id);
    }

    @GetMapping("compare/{storyId}")
    public boolean compareRequirements(@PathVariable Long storyId) throws RequirementException {
        return storyService.verifyAllRequirementsTested(storyId);
    }
}
