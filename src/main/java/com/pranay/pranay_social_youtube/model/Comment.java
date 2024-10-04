package com.pranay.pranay_social_youtube.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Comment(Integer id, String content, LocalDateTime createdAt, User user, List<User> liked) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.liked = liked;
    }

    public Comment() {
    }

    private String content;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> liked=new ArrayList<>();

    private LocalDateTime createdAt;


}
