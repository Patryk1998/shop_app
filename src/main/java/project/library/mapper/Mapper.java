package project.library.mapper;

import org.springframework.stereotype.Component;
import project.library.entities.User;
import project.library.entities.dto.UserDto;

import javax.validation.constraints.Email;

@Component
public class Mapper {

    public User userDtoToUser(UserDto userDto) {
        return new User(userDto.getEmail(), userDto.getUsername(), userDto.getPassword());
    }
}
