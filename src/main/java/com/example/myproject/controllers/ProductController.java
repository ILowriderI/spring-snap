package com.example.myproject.controllers;

import com.example.myproject.dto.ProductDTO;
import com.example.myproject.models.Image;
import com.example.myproject.models.Product;
import com.example.myproject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/{id}")
    public Long addProduct(@RequestBody Product product, @PathVariable Long id) {
        productService.saveProduct(product, id);
        return product.getId();
    }


    @PostMapping("/add-image/{id}")
    public void addImageToProduct(@PathVariable Long id, @RequestBody Image image) {

        productService.addImagesToProduct(image, id);

    }


    @GetMapping("/all")
    public ResponseEntity<?> products(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(productService.getAllProducts(pageNumber, size));

    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchProductsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(productService.searchProductByTitle(title));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDTO> productById(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getProductById(id));
    }


    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
