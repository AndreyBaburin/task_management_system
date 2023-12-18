package com.andrey_baburin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String title;
    private String description;
    private String status = "в ожидании";
    private String priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
