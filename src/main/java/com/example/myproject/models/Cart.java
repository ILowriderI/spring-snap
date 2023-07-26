package com.example.myproject.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User user;
    @OneToMany
    private List<Product> savedProducts;


}
