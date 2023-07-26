package com.example.myproject.controllers;


import com.example.myproject.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{userId}/{productId}")
    public void addToCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.addProductToCart(userId, productId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartList(@PathVariable Long userId) {

        return ResponseEntity.ok(cartService.getCartListByUser(userId));
    }

    @GetMapping("/remove/{userId}/{productId}")
    public void removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeFromSaved(userId, productId);
    }


}
