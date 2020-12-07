package com.codegym.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "note_category",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime endTime;
    private Long estimatedFinishedTime;
    private String status = "Not running";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Note() {
    }

    public Note(String title, List<Category> categories, String content, LocalDateTime startTime, LocalDateTime endTime, Long estimatedFinishedTime, String status) {
        this.title = title;
        this.categories = categories;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.estimatedFinishedTime = estimatedFinishedTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getEstimatedFinishedTime() {
        return estimatedFinishedTime;
    }

    public void setEstimatedFinishedTime(Long estimatedFinishedTime) {
        this.estimatedFinishedTime = estimatedFinishedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
