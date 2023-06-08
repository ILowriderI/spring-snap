package com.example.myproject.controllers;

import com.example.myproject.models.Image;
import com.example.myproject.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    @GetMapping("/image/{id}")
    private Optional<Image> getImageById(@PathVariable Long id){
       return   imageRepository.findById(id);
    }
}
