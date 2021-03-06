package com.eduardor.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardor.workshopmongo.domain.Post;
import com.eduardor.workshopmongo.domain.User;
import com.eduardor.workshopmongo.dto.UserDTO;
import com.eduardor.workshopmongo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.ok()
				.body(userService.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(new UserDTO(userService.findById(id)));
	}

	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		return ResponseEntity.ok().body(userService.findById(id).getPosts());
	}

	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody UserDTO dto) {
		User obj = userService.fromDTO(dto);
		obj = userService.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody User obj, @PathVariable String id) {
		obj.setId(id);
		userService.update(obj);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		userService.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
