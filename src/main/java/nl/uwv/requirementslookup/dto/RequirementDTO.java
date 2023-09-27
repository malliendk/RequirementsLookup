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
public class RequirementDTO {

    Long id;
    String name;
    String description;
    List<String> scenarioNames;
    String storyName;
    @JsonProperty("isSuccessfullyTested")
    boolean isSuccessfullyTested;
    @JsonProperty("isBuilt")
    boolean isBuilt;

}
