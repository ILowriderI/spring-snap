package com.example.myproject.services;


import com.example.myproject.dto.UserDTO;
import com.example.myproject.models.User;
import com.example.myproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public ResponseEntity<UserDTO> getUser(Integer id) {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow();
        UserDTO userDTO = new UserDTO();
        userDTO = userDTO.formEntity(user);
        return ResponseEntity.ok(userDTO);
    }


}
