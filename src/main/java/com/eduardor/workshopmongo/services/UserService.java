package com.eduardor.workshopmongo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardor.workshopmongo.domain.User;
import com.eduardor.workshopmongo.dto.UserDTO;
import com.eduardor.workshopmongo.repository.UserRepository;
import com.eduardor.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj) {
		return userRepository.insert(obj);
	}

	public void deleteById(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public User update(User obj) {
		User user = findById(obj.getId());
		BeanUtils.copyProperties(obj, user, "id");
		return userRepository.save(obj);

	}

	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}

}
