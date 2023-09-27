package nl.uwv.requirementslookup.controllers;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.RequirementDTO;
import nl.uwv.requirementslookup.entities.Requirement;
import nl.uwv.requirementslookup.services.RequirementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/requirements")
@AllArgsConstructor
public class RequirementController {

    private final RequirementService requirementService;

    @PostMapping
    public RequirementDTO create(@RequestBody RequirementDTO requirementDTO){
        return requirementService.create(requirementDTO);
    }

    @GetMapping("{id}")
    public RequirementDTO find(@PathVariable Long id){
        return requirementService.find(id);
    }

    @GetMapping("all")
    public List<RequirementDTO> all(){
        return requirementService.all();
    }

    @GetMapping("scenario/{id}")
    public List<RequirementDTO> findAllByScenario(@PathVariable Long id){
        return requirementService.findAllByScenario(id);
    }

    @GetMapping("scenario/names/{id}")
    public Set<String> findAllNamesByScenario(@PathVariable Long id){
        return requirementService.findAllNamesByScenario(id);
    }

    @GetMapping("story/{id}")
    public List<RequirementDTO> findAllByStory(@PathVariable Long id){
        return requirementService.findAllByStory(id);
    }

    @GetMapping("story/names/{id}")
    public Set<String> findAllNamesByStory(@PathVariable Long id){
        return requirementService.findAllNamesByStory(id);
    }

    @PutMapping("{id}")
    public RequirementDTO update(@RequestBody RequirementDTO requirementDTO, @PathVariable Long id){
        return requirementService.update(requirementDTO, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        requirementService.delete(id);
    }
}
