package com.example.myproject.controllers;


import com.example.myproject.models.Cart;
import com.example.myproject.models.Product;
import com.example.myproject.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart/add/{userId}/{productId}")
    public void addToCart(@PathVariable Long userId,@PathVariable Long productId){
        cartService.addProductToCart(userId,productId);
    }
    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartList(@PathVariable Long userId){
        return  cartService.getCartListByUser(userId);
    }
    @GetMapping("/cart/remove/{userId}/{productId}")
    public void removeFromCart(@PathVariable Long userId,@PathVariable Long productId){
        cartService.removeFromSaved(userId,productId);
    }



}
