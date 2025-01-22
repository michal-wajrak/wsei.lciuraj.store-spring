package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.User;

public class UserDTO extends BaseUserDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setLogin(user.getLogin());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}