package com.mycompany.recipeapi.dto;

public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String token;

    public UserDTO(){}

    public UserDTO(String jwt, Long id, String username, String email) {
        this.token = jwt;
        this.userId = id;
        this.email = email;
        this.username = username;
    }

    public UserDTO(String username, String email, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
