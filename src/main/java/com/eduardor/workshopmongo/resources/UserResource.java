package com.eduardor.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody UserDTO dto) {
		User obj = userService.fromDTO(dto);
		obj = userService.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
