package com.weilai.model.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CodeLoginDTO {


    @NotBlank
    private String code;

    @Email
    @NotBlank
    private String email;


}
