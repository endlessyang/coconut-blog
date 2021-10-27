package com.coconut.model.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RegisterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "The username can't be blank")
    private String username;

    @NotBlank(message = "The password can't be blank")
    private String password;

    @NotBlank(message = "The email can't be blank")
    private String email;

}
