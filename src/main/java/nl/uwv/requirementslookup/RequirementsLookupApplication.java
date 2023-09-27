package nl.uwv.requirementslookup;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.uwv.requirementslookup.entities.Requirement;
import nl.uwv.requirementslookup.entities.Scenario;
import nl.uwv.requirementslookup.entities.Story;
import nl.uwv.requirementslookup.repositories.RequirementRepository;
import nl.uwv.requirementslookup.repositories.ScenarioRepository;
import nl.uwv.requirementslookup.repositories.StoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class RequirementsLookupApplication implements CommandLineRunner {

    private final RequirementRepository requirementRepository;
    private final ScenarioRepository scenarioRepository;
    private final StoryRepository storyRepository;

    public static void main(String[] args) {
        SpringApplication.run(RequirementsLookupApplication.class, args);
    }

    @Override
    @Transactional
    public void run(final String... args) throws Exception {

        Story story = new Story();
        story.setName("Technische validatie CSV");
        story.setDescription("De inhoud van geïmporteerde CSV-bestanden wordt gevalideerd");
        story.setAllRequirementsSuccesfullyTested(false);

        storyRepository.save(story);
        Requirement requirement1 = new Requirement();
        requirement1.setName("Verkeerde header geeft crash");
        requirement1.setDescription("Als een van de headers van het geïmporteerde CSV bestand een onjuiste naam heeft, dan crasht de applicatie (exception)");
        requirement1.setSuccessfullyTested(false);
        requirement1.setStory(story);
        requirementRepository.save(requirement1);
        Requirement requirement11 = new Requirement();
        requirement11.setName("Geslaagd importeren bij correcte headers");
        requirement11.setDescription("Wanneer alle headers de juiste naam hebben, wordt het bestand succesvol geïmporteerd");
        requirement11.setSuccessfullyTested(false);
        requirement11.setStory(story);
        requirementRepository.save(requirement11);
        Requirement requirement2 = new Requirement();
        requirement2.setName("Leeg verplicht veld geeft crasht");
        requirement2.setDescription("Wanneer een verplicht veld uit het geïmporteerde CSV bestand een leeg is, dan crasht de applicatie (exception)");
        requirement2.setSuccessfullyTested(false);
        requirement2.setStory(story);
        requirementRepository.save(requirement2);
        Requirement requirement3 = new Requirement();
        requirement3.setName("Incorrect datumformat geeft crash");
        requirement3.setDescription("Als een van de datums een onjuist format heeft, dan crasht de applicatie (exception)");
        requirement3.setSuccessfullyTested(false);
        requirement3.setStory(story);
        requirementRepository.save(requirement3);
        Requirement requirement4 = new Requirement();
        requirement4.setName("Exception message bevat veldnaam");
        requirement4.setDescription("Wanneer een exception wordt gegooid, dan bevat de message van die exception de naam van het veld waar de applicatie op is gecrasht");
        requirement4.setSuccessfullyTested(false);
        requirement4.setStory(story);
        requirementRepository.save(requirement4);
        Requirement requirement5 = new Requirement();
        requirement5.setName("Logregel bij crash");
        requirement5.setDescription("Wanneer een fout optreedt, dan wordt er een logregel weggeschreven met details van de betreddende fout");
        requirement5.setSuccessfullyTested(false);
        requirement5.setStory(story);
        requirementRepository.save(requirement5);
        Requirement requirement6 = new Requirement();
        requirement6.setName("Logregel bij geslaagd importeren");
        requirement6.setDescription("Wanneer een CSV bestand met succes is geïmporteerd, wordt hierover een logregel geschreven");
        requirement6.setSuccessfullyTested(false);
        requirement6.setStory(story);
        requirementRepository.save(requirement6);

        Scenario scenario1 = new Scenario();
        scenario1.setName("Lege header");
        scenario1.setDescription("Het input CSV bestand mist een header op kolom BSN");
        scenario1.setSuccessfullyTested(false);
        scenario1.setRequirements(Set.of(requirement1, requirement4, requirement5));
        scenario1.setStory(story);
        scenarioRepository.save(scenario1);
        Scenario scenario2 = new Scenario();
        scenario2.setName("Incorrect datumformat");
        scenario2.setDescription("Het input CSV bestand bevat een datum met ee ongeldig format in kolom begin_grondslag");
        scenario2.setSuccessfullyTested(false);
        scenario2.setRequirements(Set.of(requirement3, requirement4, requirement5));
        scenario2.setStory(story);
        scenarioRepository.save(scenario2);
        Scenario scenario3 = new Scenario();
        scenario3.setName("Leeg verplicht veld");
        scenario3.setDescription("Het input CSV bestand bevat een lege cel in verplchte kolom BSN");
        scenario3.setSuccessfullyTested(false);
        scenario3.setRequirements(Set.of(requirement2, requirement4, requirement5));
        scenario3.setStory(story);
        scenarioRepository.save(scenario3);
        Scenario scenario4 = new Scenario();
        scenario4.setName("Alle headers gevuld");
        scenario4.setDescription("Alle headers van het input CSV bestand zijn gevuld");
        scenario4.setSuccessfullyTested(false);
        scenario4.setRequirements(Set.of(requirement11, requirement6));
        scenario4.setStory(story);
        scenarioRepository.save(scenario4);

        story.setRequirements(new ArrayList<>(List.of(requirement1,requirement2,requirement3,requirement4,
                requirement5,requirement6,requirement11)));
        story.setScenarios(new ArrayList<>(List.of(scenario1,scenario2,scenario3,scenario4)));
        storyRepository.save(story);

    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowCredentials(true)
//                        .allowedHeaders("*");
//            }
//        };
//    }
}
