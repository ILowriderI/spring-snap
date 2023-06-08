package com.example.myproject.controllers;


import com.example.myproject.models.Message;
import com.example.myproject.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;


    @PostMapping("/message/{senderId}/send/{recipientId}")
    public void sendMessage (
            @PathVariable Integer senderId,
            @PathVariable Integer recipientId,
            @RequestBody Message message){

        chatService.sendMessage(senderId,recipientId,message);
    }

    @GetMapping("/chats/user/{id}")
    public ResponseEntity <?> getChatsUser(@PathVariable Integer id){
        return chatService.getChatsByUser(id);
    }

    @GetMapping ("/chat/message/last/{id}")
    public ResponseEntity<Message> getLastMessage (@PathVariable Integer id){
        return  chatService.findLastMessageInChat(id);
    }
    @GetMapping("/chat/messages/{id}/{senderId}")
    public ResponseEntity<?> getAllMessageByChat(@PathVariable Integer id,@PathVariable Integer senderId){
        return chatService.getAllMessageByChat(id,senderId);
    }
}
