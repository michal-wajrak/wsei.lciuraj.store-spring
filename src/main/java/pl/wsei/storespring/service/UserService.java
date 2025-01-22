package pl.wsei.storespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.wsei.storespring.dto.BaseUserDTO;
import pl.wsei.storespring.dto.UserDTO;
import pl.wsei.storespring.exception.ResourceNotFoundException;
import pl.wsei.storespring.model.User;
import pl.wsei.storespring.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(UserDTO::fromEntity)
            .toList();
    }

    public UserDTO getUserById(Long id) {
        return UserDTO.fromEntity(userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }

    public UserDTO createUser(BaseUserDTO baseUserDTO) {
        if (userRepository.existsByLogin((baseUserDTO.getLogin()))) {
            throw new DataIntegrityViolationException("Login must be unique");
        }

        User user = baseUserDTO.toEntity();
        user = userRepository.save(user);
        return UserDTO.fromEntity(user);
    }

    public UserDTO updateUser(Long id, BaseUserDTO baseUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (baseUserDTO.getName() != null) {
            user.setName(baseUserDTO.getName());
        }

        if (baseUserDTO.getSurname() != null) {
            user.setSurname(baseUserDTO.getSurname());
        }

        if (baseUserDTO.getLogin() != null && !baseUserDTO.getLogin().equals(user.getLogin())) {
            if (userRepository.existsByLogin(baseUserDTO.getLogin())) {
                throw new DataIntegrityViolationException("Login must be unique");
            }

            user.setLogin(baseUserDTO.getLogin());
        }

        if (baseUserDTO.getEmail() != null) {
            user.setEmail(baseUserDTO.getEmail());
        }

        user = userRepository.save(user);

        return UserDTO.fromEntity(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }
}
