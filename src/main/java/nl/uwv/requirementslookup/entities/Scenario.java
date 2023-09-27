package nl.uwv.requirementslookup.entities;


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
public class Scenario {

    @Id
    @GeneratedValue
    Long id;

    @NotBlank(message = "Scenario name may not be empty")
    String name;

    @NotBlank(message = "Scenario description may not be empty")
    String description;

    @ManyToMany
    Set<Requirement> requirements;

    @ManyToOne
    Story story;

    boolean isSuccessfullyTested;
}
