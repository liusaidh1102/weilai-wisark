package com.weilai.model.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class EmailLoginDTO {

    @NotBlank
    private String pwd;
    @Email
    @NotBlank
    private String email;
}
