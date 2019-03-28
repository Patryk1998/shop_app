package project.library.mapper;

import org.springframework.stereotype.Component;
import project.library.entities.dto.TitleDtoGet;
import project.library.entities.dto.TitleDtoPost;
import project.library.entities.library.Title;
import project.library.entities.login.User;
import project.library.entities.dto.UserDto;

@Component
public class Mapper {

    public User userDtoToUser(UserDto userDto) {
        return new User(userDto.getEmail(), userDto.getUsername(),
                userDto.getName(), userDto.getSurname(), userDto.getPassword());
    }

    public TitleDtoGet titleToTitleDto(Title title) {
        return new TitleDtoGet(title.getTitleId(), title.getTitle(), title.getAuthor(),
                title.getSpendYear(), title.getType().getName(), title.getPieces().size());
    }

    public Title titleDtoToTitle(TitleDtoPost titleDto) {
        return new Title(titleDto.getTitle(), titleDto. getAuthor(), titleDto.getSpendYear());
    }

}
