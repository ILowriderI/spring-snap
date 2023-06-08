package com.example.myproject.services;


import com.example.myproject.dto.ProductDTO;
import com.example.myproject.models.Image;
import com.example.myproject.models.Product;

import com.example.myproject.models.User;
import com.example.myproject.repositories.ImageRepository;
import com.example.myproject.repositories.ProductRepository;
import com.example.myproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;



@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final CartService cartService;



    public ResponseEntity<?> getAllProducts(Integer pageNumber, Integer size) {
        Page <Product> products = productRepository.findAll(PageRequest.of(pageNumber, size));

        Page <ProductDTO> productsDTO = products.map(item->{
           ProductDTO productDTO= new ProductDTO();
            return productDTO.formEntity(item);
        });
        return ResponseEntity.ok(convertToResponse(productsDTO));

    }

    public ResponseEntity<?> searchProductByTitle( String title){
        List<Product> products = productRepository.findProductsByTitleContainsIgnoreCase(title);
        List<ProductDTO> productDTOList = new ArrayList<>();
        products.forEach(item->{
           ProductDTO  productDTO = new ProductDTO();
            productDTOList.add(productDTO.formEntity(item));
        });

        return ResponseEntity.ok(productDTOList);

    }


    private Map<String, Object> convertToResponse(final Page<ProductDTO> pageProductDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("products", pageProductDto.getContent());
        response.put("current_page", pageProductDto.getNumber());
        response.put("total_items", pageProductDto.getTotalElements());
        response.put("total_pages", pageProductDto.getTotalPages());
        return response;
    }




    public  ResponseEntity<ProductDTO> getProductById(Long id) {

        Product product = productRepository.findById(id).orElseThrow();
        ProductDTO productDTO = new ProductDTO();
        return  ResponseEntity.ok( productDTO.formEntity(product));

    }


    public void saveProduct(Product product,Long id) {

        User user = userRepository.findById(id).orElseThrow();
        user.addProductToUser(product);
        productRepository.save(product);
    }


        public void addImagesToProduct(Image image, Long id) {

        Product product = productRepository.findById(id).orElseThrow();
        product.addImageToProduct(image);
        imageRepository.save(image);
        productRepository.save(product);

    }



    public void deleteProduct(Long id) {
        Integer userId = productRepository.findById(id).orElseThrow().getUser().getId();
        cartService.removeFromSaved(Long.valueOf(userId),id);
        productRepository.deleteById(id);
    }







}











