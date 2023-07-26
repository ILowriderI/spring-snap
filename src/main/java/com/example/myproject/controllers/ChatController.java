package com.example.myproject.controllers;

import com.example.myproject.models.Message;
import com.example.myproject.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/message/{senderId}/send/{recipientId}")
    public void sendMessage(@PathVariable Integer senderId, @PathVariable Integer recipientId, @RequestBody Message message) {

        chatService.sendMessage(senderId, recipientId, message);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getChatsUser(@PathVariable Integer id) {

        return ResponseEntity.ok(chatService.getChatsByUser(id));
    }

    @GetMapping("/message/last/{id}")
    public ResponseEntity<Message> getLastMessage(@PathVariable Integer id) {
        return ResponseEntity.ok(chatService.findLastMessageInChat(id));
    }

    @GetMapping("/messages/{id}/{senderId}")
    public ResponseEntity<?> getAllMessageByChat(@PathVariable Integer id, @PathVariable Integer senderId) {
        return ResponseEntity.ok(chatService.getAllMessageByChat(id, senderId));
    }
}
