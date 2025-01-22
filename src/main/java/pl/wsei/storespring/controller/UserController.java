package pl.wsei.storespring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsei.storespring.dto.BaseUserDTO;
import pl.wsei.storespring.dto.UserDTO;
import pl.wsei.storespring.service.UserService;

import java.util.List;

@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Operation(summary = "Get all users")
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@Operation(summary = "Get user by ID")
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		UserDTO user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@Operation(summary = "Create a new user")
	@PostMapping("/user")
	public ResponseEntity<UserDTO> createUser(@RequestBody BaseUserDTO baseUserDTO) {
		UserDTO createdUser = userService.createUser(baseUserDTO);
		return ResponseEntity.status(201).body(createdUser);
	}

	@Operation(summary = "Update an existing user")
	@PutMapping("/user/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody BaseUserDTO baseUserDTO) {
		UserDTO updatedUser = userService.updateUser(id, baseUserDTO);
		return ResponseEntity.ok(updatedUser);
	}

	@Operation(summary = "Delete a user")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}