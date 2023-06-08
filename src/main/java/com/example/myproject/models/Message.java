package com.example.myproject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private Boolean isRead;
    private String body;
    private Integer sender;
    private Integer recipient;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Chat chat;


    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }



}
