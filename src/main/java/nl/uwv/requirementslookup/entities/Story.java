package nl.uwv.requirementslookup.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Story {

    @Id
    @GeneratedValue
    Long id;

    @NotBlank
    String name;

    @NotBlank
    String description;

    @OneToMany
    List<Scenario> scenarios;

    @OneToMany
    List<Requirement> requirements;

    @JsonProperty("isAllRequirementsSuccesfullyTested")
    boolean isAllRequirementsSuccesfullyTested;
}
