package com.example.myproject.services;


import com.example.myproject.models.Chat;
import com.example.myproject.models.Message;
import com.example.myproject.repositories.ChatRepository;
import com.example.myproject.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public void sendMessage(Integer senderId, Integer recipientId, Message message) {
        List<Chat> chatList = chatRepository.findAll();
        AtomicBoolean isChatPresent = new AtomicBoolean(false);
        chatList.forEach(item -> {
            if (item.getSenderId().equals(senderId) && item.getRecipientId().equals(recipientId) ||
                    item.getSenderId().equals(recipientId) && item.getRecipientId().equals(senderId)
            ) {
                isChatPresent.set(true);
            }
        });
        message.setSender(senderId);
        message.setRecipient(recipientId);
        message.setIsRead(false);

        if (!isChatPresent.get()) {
            Chat newChat = new Chat();
            newChat.setSenderId(senderId);
            newChat.setRecipientId(recipientId);
            newChat.getMessageList().add(message);
            newChat.addMessageToChat(message);
            chatRepository.save(newChat);
            message.setChat(newChat);
            messageRepository.save(message);
        } else {
            Chat chat;
            if (chatRepository.findChatBySenderIdAndRecipientId(senderId, recipientId) == null) {
                chat = chatRepository.findChatBySenderIdAndRecipientId(recipientId, senderId);
            } else {
                chat = chatRepository.findChatBySenderIdAndRecipientId(senderId, recipientId);
            }

            chat.getMessageList().add(message);
            chat.addMessageToChat(message);
            message.setChat(chat);
            messageRepository.save(message);
            chatRepository.save(chat);

        }

    }


    public List<Chat> getChatsByUser(Integer id) {
        Integer id1 = id;
        return chatRepository.findChatsByRecipientIdOrSenderIdOrderById(id, id1);
    }

    public Message findLastMessageInChat(Integer id) {
        Chat chat = chatRepository.findById(Long.valueOf(id)).orElseThrow();
        List<Message> messageList = messageRepository.findMessageByChat(chat);
        return messageList.get(messageList.size() - 1);

    }

    public List<Message> getAllMessageByChat(Integer id, Integer senderId) {
        Chat chat = chatRepository.findById(Long.valueOf(id)).orElseThrow();
        List<Message> messageList = messageRepository.findMessageByChat(chat);
        messageList.forEach(item -> {
            Message lastMessage = findLastMessageInChat(id);
            if (!Objects.equals(lastMessage.getSender(), senderId)) {
                item.setIsRead(true);
            }

            messageRepository.save(item);
        });

        return messageList;
    }

}
