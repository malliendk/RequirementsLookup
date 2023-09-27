package nl.uwv.requirementslookup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class StoryDTO {

    Long id;
    String name;
    String description;
    List<String> requirementNames;
    List<String> scenarioNames;

    @JsonProperty("isAllRequirementsSuccessfullyTested")
    boolean isAllRequirementsSuccessfullyTested;
}
