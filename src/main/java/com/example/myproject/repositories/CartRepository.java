package com.example.myproject.repositories;

import com.example.myproject.models.Cart;
import com.example.myproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart findCartByUser(User user);

}
