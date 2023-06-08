package com.example.myproject.repositories;

import com.example.myproject.models.Chat;
import com.example.myproject.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {


    List <Chat> findChatsByRecipientIdOrSenderIdOrderById(Integer id,Integer id1);

    Chat findChatBySenderIdAndRecipientId(Integer sender,Integer recipient);
}
