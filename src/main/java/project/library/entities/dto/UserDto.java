package project.library.entities.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UserDto {
    private String email;
    private String username;
    private String password;
    private String passwordConfirm;
}
