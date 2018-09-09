package com.eduardor.workshopmongo.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.eduardor.workshopmongo.domain.Post;
import com.eduardor.workshopmongo.domain.User;
import com.eduardor.workshopmongo.repository.PostRepository;
import com.eduardor.workshopmongo.repository.UserRepository;

@Configuration
public class instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository repo;

	@Autowired
	private PostRepository PostRepository;

	@Override
	public void run(String... args) throws Exception {

		repo.deleteAll();
		PostRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		Post post1 = new Post(null, LocalDate.parse("2018-09-07"), "Partiu viagem",
				"Vou viagar para São Paulo. Abraços!", maria);
		Post post2 = new Post(null, LocalDate.parse("2018-07-24"), "Bom dia!", "Acordei feliz hoje!", maria);

		repo.saveAll(Arrays.asList(maria, alex, bob));
		PostRepository.saveAll(Arrays.asList(post1, post2));
	}

}
