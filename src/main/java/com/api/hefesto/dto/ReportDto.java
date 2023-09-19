package com.api.hefesto.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("queue")
    private String queue;
    @JsonProperty("mail")
    private Boolean mail;
    @JsonProperty("User")
    private UserDto User;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("deleted")
    private Boolean deleted;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("updatedAt")
    private Date updatedAt;

    public ReportDto() {
    }

    public ReportDto(String id, String name, String type, String queue, Boolean mail, UserDto User, String userId, Boolean enabled, Boolean deleted, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.queue = queue;
        this.mail = mail;
        this.User = User;
        this.userId = userId;
        this.enabled = enabled;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type= type;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Boolean getMail() {
        return mail;
    }

    public void setMail(Boolean mail) {
        this.mail = mail;
    }

    public UserDto getUser() {
        return User;
    }

    public void setUser(UserDto User) {
        this.User = User;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId= userId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ReportDto [id=" + id + ", name=" + name + ", type=" + type + ", queue=" + queue + ", mail=" + mail + ", User=" + User + ", userId=" + userId + ", enabled=" + enabled + ", deleted=" + deleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
