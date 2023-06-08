package com.example.myproject.dto;




import com.example.myproject.models.Product;
import com.example.myproject.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private String name;
    private List<Product> productList = new ArrayList<>();
    private LocalDateTime dateOfCreated;


    public  UserDTO formEntity(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(Long.valueOf(user.getId()));
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setDateOfCreated(user.getDateOfCreated());
        userDTO.setProductList(user.getProductList());
        return userDTO;
    }



}
