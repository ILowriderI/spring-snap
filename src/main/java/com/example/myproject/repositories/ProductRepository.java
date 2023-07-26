package com.example.myproject.repositories;


import com.example.myproject.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Page<Product> findAll(final Pageable pageable);

    List<Product> findProductsByTitleContainsIgnoreCase(String title);


}
