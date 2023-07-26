package com.example.myproject.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {


    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_#$~`]{3,29}$", message = "incorrect user name")
    private String name;
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "incorrect phone number")
    private String phoneNumber;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "incorrect email")
    private String email;
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[-\\#\\$\\.\\%\\&\\*])(?=.*[a-zA-Z]).{8,16}$",
            message = "incorrect password")
    private String password;
}

