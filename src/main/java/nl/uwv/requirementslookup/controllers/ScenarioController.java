package nl.uwv.requirementslookup.controllers;

import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.dto.ScenarioDTO;
import nl.uwv.requirementslookup.services.ScenarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/scenarios")
@AllArgsConstructor
public class ScenarioController {

    private final ScenarioService scenarioService;

    @PostMapping
    public ScenarioDTO create(@RequestBody ScenarioDTO scenarioDTO) {
        return scenarioService.create(scenarioDTO);
    }

    @GetMapping("{id}")
    public ScenarioDTO find(@PathVariable Long id) {
        return scenarioService.find(id);
    }

    @GetMapping("all")
    public List<ScenarioDTO> all() {
        return scenarioService.all();
    }

    @GetMapping("story/{id}")
    public List<ScenarioDTO> findAllByStory(@PathVariable Long id){
        return scenarioService.findAllByStory(id);
    }

    @PutMapping("{id}")
    public ScenarioDTO update(@RequestBody ScenarioDTO scenarioDTO, @PathVariable Long id){
        return scenarioService.update(scenarioDTO, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        scenarioService.delete(id);
    }

    @PutMapping("tested/{id}")
    public ScenarioDTO setRequirementsTestedToTrue(@RequestBody ScenarioDTO scenarioDTO, @PathVariable Long id){
        return scenarioService.setRequirementsTestedToTrue(scenarioDTO, id);
    }
}