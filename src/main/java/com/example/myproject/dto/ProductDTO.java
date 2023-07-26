package com.example.myproject.dto;


import com.example.myproject.models.Image;
import com.example.myproject.models.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {


    private Long id;
    private String title;
    private String description;
    private int price;
    private String city;
    private List<Image> imageList = new ArrayList<>();
    private LocalDateTime dateOfCreated;
    private Integer userId;
    private String userName;


    public ProductDTO formEntity(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCity(),
                product.getImageList(),
                product.getDateOfCreated(),
                product.getUser().getId(),
                product.getUser().getName()
        );
    }

}
