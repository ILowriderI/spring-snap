package com.example.myproject.controllers;

import com.example.myproject.dto.ProductDTO;
import com.example.myproject.models.Image;
import com.example.myproject.models.Product;
import com.example.myproject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;



    @PostMapping("/product/create/{id}")
    public Long addProduct(@RequestBody Product product ,@PathVariable Long id) {
        productService.saveProduct(product,id);
        return product.getId();
    }



    @PostMapping("/product/add-image/{id}")
    public void addImg(@PathVariable Long id, @RequestBody Image image) {

        productService.addImagesToProduct(image, id);

    }


@GetMapping("/products")
public ResponseEntity<?> products(
        @RequestParam(defaultValue = "0")  Integer pageNumber,
        @RequestParam(defaultValue = "5")  Integer size
) {
    return productService.getAllProducts(pageNumber,size);

}

@GetMapping("/products/search/{title}")
public  ResponseEntity<?> searchProductsByTitle(@PathVariable String title){
     return productService.searchProductByTitle( title);
}

    @GetMapping("/product/id/{id}")
    public ResponseEntity<ProductDTO> productById(@PathVariable Long id) {
        return productService.getProductById(id);
    }



    @GetMapping("/product/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }




}


