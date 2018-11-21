package project.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.library.dao.UserDao;
import project.library.entities.User;
import project.library.entities.dto.UserDto;
import project.library.enums.ExceptionMessages;
import project.library.exceptions.LoginException;
import project.library.exceptions.RegistrationException;
import project.library.mapper.Mapper;
import project.library.validator.Validator;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ExceptionMessages exceptionMessages;

    @Autowired
    private Validator validator;

    public boolean addNewAccount(UserDto userDto) throws RegistrationException {

        if(validator.validate(userDto.getEmail(), validator.EMAIL_PATTERN)) throw new RegistrationException(exceptionMessages.EMAIL_WRONG_FORM);
        if(checkIfEmailExist(userDto.getEmail())) throw new RegistrationException(exceptionMessages.EMAIL_EXIST);
        if(checkIfUsernameExist(userDto.getUsername())) throw new RegistrationException(exceptionMessages.USERNAME_EXIST);
        if(checkIfPasswordsSame(userDto)) throw new RegistrationException(exceptionMessages.PASSWORDS_NOT_EQUALS);

        User newUser = userDao.save(mapper.userDtoToUser(userDto));

        if(newUser.getEmail().equals(userDto.getEmail()) &&
                newUser.getUsername().equals(userDto.getUsername()) && newUser.getUsername().equals(userDto.getUsername())) {
            return true;
        } else return false;
    }

    public boolean login(UserDto userDto) throws LoginException {
        User user = getUserByUsername(userDto.getUsername()).orElseThrow(() -> new LoginException(exceptionMessages.USERNAME_NOT_EXIST));
        if(userDto.getPassword().equals(user.getPassword())) {
            return true;
        } else throw new LoginException(exceptionMessages.PASSWORD_NOT_CORRECT);
    }

    //jeżeli hasła są takie same to zwraca false tak aby nie został rzucony exception w metodzie wywołującej
    public boolean checkIfPasswordsSame(UserDto userDto) {
        if(userDto.getPassword().equals(userDto.getPasswordConfirm())) return false;
        else return true;
    }

    public Optional<User> getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public boolean checkIfUsernameExist(String username) {
        return userDao.existsByUsername(username);
    }

    public boolean checkIfEmailExist(String email) {
        return userDao.existsByEmail(email);
    }
}
