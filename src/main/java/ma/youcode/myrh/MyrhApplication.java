package ma.youcode.myrh;


import ma.youcode.myrh.dtos.AgentDTO;
import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.QuestionDTO;
import ma.youcode.myrh.models.JobSeeker;
import ma.youcode.myrh.models.Profile;
import ma.youcode.myrh.repositories.UserRepository;
import ma.youcode.myrh.services.implementations.AgentService;
import ma.youcode.myrh.services.implementations.ProfileService;
import ma.youcode.myrh.services.implementations.QuestionService;
import ma.youcode.myrh.services.implementations.RecruiterService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class MyrhApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyrhApplication.class, args);
    }
	@Bean
    CommandLineRunner start(
			ModelMapper modelMapper,
			AgentService agentService, ProfileService profileService, QuestionService questionService){
		return args -> {
//			AgentDTO agentDTO = new AgentDTO();
//			agentDTO.setName("Agent PM");
//			agentDTO.setEmail("elgountariayoub21@gmail.com");
//			agentDTO.setPassword("agent2024");
//			agentDTO.setRole("AGENT");
//			agentService.save(agentDTO);

//			ProfileDTO profileDTO = profileService.findByTitle("Java Developer");
//
//			JobSeeker jobSeeker = new JobSeeker();
//			jobSeeker.setName("");


//			//Create a profile
//			ProfileDTO profileDTO = new ProfileDTO();
//			profileDTO.setTitle("Java Developer");
//			profileDTO = profileService.save(profileDTO);
//
//			//Create a question
//			QuestionDTO questionDTO = new QuestionDTO();
//
//			questionDTO.setContent("Is java an OOP language");
//			questionDTO.setAnswer(true);
//
//			questionDTO.setProfileDTO(profileDTO);
//
//			questionService.save(questionDTO);
		};
	}
}
