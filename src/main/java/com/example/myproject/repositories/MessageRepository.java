package com.example.myproject.repositories;


import com.example.myproject.models.Chat;
import com.example.myproject.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findMessageByChat(Chat chat);
}
