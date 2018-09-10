package com.eduardor.workshopmongo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardor.workshopmongo.domain.Post;
import com.eduardor.workshopmongo.repository.PostRepository;
import com.eduardor.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Post findById(String id) {
		return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	// public List<Post> findByTitle(String text) {
	// return postRepository.findByTitleContainingIgnoreCase(text);
	// }

	public List<Post> findByTitle(String text) {
		return postRepository.findByTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 20 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}

}
