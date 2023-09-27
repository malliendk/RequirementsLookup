package nl.uwv.requirementslookup.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Requirement {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @NotBlank(message = "Requirement description may not be empty")
    String description;

    @ManyToMany
    Set<Scenario> scenarios;

    @ManyToOne
    Story story;

    @JsonProperty("isSuccessfullyTested")
    boolean isSuccessfullyTested;

    @JsonProperty("isBuilt")
    boolean isBuilt;
}
