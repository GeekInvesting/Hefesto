package com.api.hefesto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("userPassword")
    private String userPassword;
    @JsonProperty("userEmail")
    private String userEmail;
    @JsonProperty("UserRole")
    private String UserRole;
    @JsonProperty("userEnabled")
    private Boolean userEnabled;
    @JsonProperty("userDeleted")
    private Boolean userDeleted;
    @JsonProperty("userCreatedAt")
    private String userCreatedAt;
    @JsonProperty("userUpdatedAt")
    private String userUpdatedAt;


    @JsonProperty("User")
    private UserType user;

    public UserDto() {
    }

    public UserDto(String id, String userName, String userPassword, String userEmail, String UserRole, Boolean userEnabled, Boolean userDeleted, String userCreatedAt, String userUpdatedAt) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.UserRole = UserRole;
        this.userEnabled = userEnabled;
        this.userDeleted = userDeleted;
        this.userCreatedAt = userCreatedAt;
        this.userUpdatedAt = userUpdatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }

    public String getUserRole() {
        return UserRole;
    }

    public UserType getUser() {
        return user;
    }

    public void setUser(UserType user) {
        this.user = user;
    }

    public void setUserEnabled(Boolean userEnabled) {
        this.userEnabled = userEnabled;
    }

    public Boolean getUserEnabled() {
        return userEnabled;
    }

    public void setUserDeleted(Boolean userDeleted) {
        this.userDeleted = userDeleted;
    }

    public Boolean getUserDeleted() {
        return userDeleted;
    }

    public void setUserCreatedAt(String userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }

    public String getUserCreatedAt() {
        return userCreatedAt;
    }

    public void setUserUpdatedAt(String userUpdatedAt) {
        this.userUpdatedAt = userUpdatedAt;
    }

    public String getUserUpdatedAt() {
        return userUpdatedAt;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", UserRole='" + UserRole + '\'' +
                ", userEnabled=" + userEnabled +
                ", userDeleted=" + userDeleted +
                ", userCreatedAt='" + userCreatedAt + '\'' +
                ", userUpdatedAt='" + userUpdatedAt + '\'' +
                ", user=" + user +
                '}';
    }
}
