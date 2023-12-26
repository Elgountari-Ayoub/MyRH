package ma.youcode.myrh.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruiterDTO {
    private Long id;

    private String login;
    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "phone is required")
    private String phone;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "image is required")
    private String image;
}
