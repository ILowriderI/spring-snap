package com.example.myproject.services;

import com.example.myproject.dto.ProductDTO;
import com.example.myproject.models.Cart;
import com.example.myproject.models.Product;
import com.example.myproject.models.User;
import com.example.myproject.repositories.CartRepository;
import com.example.myproject.repositories.ProductRepository;
import com.example.myproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addProductToCart(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow();
        Cart cartRep = cartRepository.findCartByUser(user);
        Product product = productRepository.findById(productId).orElseThrow();

        if (cartRep == null) {
            List<Product> list = new ArrayList<>();
            list.add(product);
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setSavedProducts(list);
            user.setCart(cart);

            cartRepository.save(cart);
        } else {
            cartRep.setUser(user);
            cartRep.getSavedProducts().add(product);
            user.setCart(cartRep);

            cartRepository.save(cartRep);
        }

        userRepository.save(user);

    }

    public List<ProductDTO> getCartListByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.findCartByUser(user);
        if (cart == null) {
            List<ProductDTO> empty = new ArrayList<>();
            return empty;
        }
        List<Product> products = cart.getSavedProducts();
        List<ProductDTO> productDTOList = new ArrayList<>();
        products.forEach(item -> {
            ProductDTO productDTO = new ProductDTO();
            productDTOList.add(productDTO.formEntity(item));
        });
        return productDTOList;
    }

    public void removeFromSaved(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.findCartByUser(user);
        List<Product> products = cart.getSavedProducts();
        products.removeIf(item -> item.getId().equals(productId));
        cartRepository.save(cart);

    }

}
