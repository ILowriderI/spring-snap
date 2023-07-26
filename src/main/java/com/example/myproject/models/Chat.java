package com.example.myproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Chat {
    @Id
    @GeneratedValue
    private Long id;
    private Integer senderId;
    private Integer recipientId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "chat")
    private List<Message> messageList = new ArrayList<>();

    public void addMessageToChat(Message message) {
        message.setChat(this);
        messageList.add(message);
    }

}
