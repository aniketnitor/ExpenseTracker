package com.example.expensetracker;

import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpensetrackerApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

    public static void main(String[] args) {
		SpringApplication.run(ExpensetrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUsername("user");
		user1.setPassword("123");
		userRepository.save(user1);

		User user2 = new User();
		user2.setUsername("aniket");
		user2.setPassword("456");
		userRepository.save(user2);
	}
}
