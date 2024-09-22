package com.example.backend;

import com.example.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository repository){
		return args -> {
//			User user = repository.findById("64110cf12bce0c136aa1ef3d");
//			user.setAge(222);
//			repository.save(user);
			//Chat chat = new Chat(1L,2L,"",true,true,2L);
			//repository.save(chat);
			//Chat c;
			//c = repository.findByReceiverId(2L);

		};


	}


}
