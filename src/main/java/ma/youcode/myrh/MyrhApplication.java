package ma.youcode.myrh;


import ma.youcode.myrh.dtos.AgentDTO;
import ma.youcode.myrh.repositories.UserRepository;
import ma.youcode.myrh.services.implementations.AgentService;
import ma.youcode.myrh.services.implementations.RecruiterService;
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
    CommandLineRunner start(AgentService agentService, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			AgentDTO agentDTO = new AgentDTO();
			agentDTO.setName("Agent PM");
			agentDTO.setEmail("elgountariayoub21@gmail.com");
            String password = passwordEncoder.encode("agentpassword");
			agentDTO.setPassword(password);
			agentService.save(agentDTO);
//            userRepository.save(agentDTO);
		};
	}
}
