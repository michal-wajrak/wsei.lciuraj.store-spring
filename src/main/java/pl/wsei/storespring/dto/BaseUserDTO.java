package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.User;

public class BaseUserDTO {
    private String name;
    private String surname;
    private String login;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setLogin(this.login);
        user.setEmail(this.email);
        return user;
    }
}
