package com.eduardor.workshopmongo.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.eduardor.workshopmongo.domain.Post;
import com.eduardor.workshopmongo.domain.User;
import com.eduardor.workshopmongo.dto.AuthorDTO;
import com.eduardor.workshopmongo.dto.CommentDTO;
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

		repo.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, LocalDate.parse("2018-09-07"), "Partiu viagem",
				"Vou viagar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, LocalDate.parse("2018-07-24"), "Bom dia!", "Acordei feliz hoje!",
				new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem mano", LocalDate.parse("2018-07-24"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveita", LocalDate.parse("2018-08-22"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", LocalDate.parse("2018-08-30"), new AuthorDTO(alex));

		post1.getCommentDTOs().addAll(Arrays.asList(c1, c2));
		post2.getCommentDTOs().addAll(Arrays.asList(c3));

		PostRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));

		repo.save(maria);
	}

}
