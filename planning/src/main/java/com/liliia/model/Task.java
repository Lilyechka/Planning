package com.liliia.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_task;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private LocalDateTime date_of_creation;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task() {
        this.date_of_creation = LocalDateTime.now();
    }

    public Task(String topic, LocalDateTime deadline_date, String description, User user) {
        this.topic = topic;
        this.date_of_creation = LocalDateTime.now();
        this.deadline = deadline_date;
        this.description = description;
        this.user = user;
    }


    public Integer getId() {
        return id_task;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDateTime getCreation_date() {
        return date_of_creation;
    }

    public LocalDateTime getDeadline_date() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id_task = id;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.date_of_creation = creation_date;
    }

    public void setDeadline_date(LocalDateTime deadline_date) {
        this.deadline = deadline_date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id_task +
                ", topic='" + topic + '\'' +
                ", creation_date=" + date_of_creation +
                ", deadline_date=" + deadline +
                ", description='" + description + '\'' +
                '}';
    }
}
