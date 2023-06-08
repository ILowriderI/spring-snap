package com.example.myproject.controllers;

import com.example.myproject.dto.UserDTO;
import com.example.myproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
     return userService.getUser(id);
    }


}
