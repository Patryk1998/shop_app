package project.library.services;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.library.dao.UserDao;
import project.library.dao.shop.RoleDao;
import project.library.entities.Role;
import project.library.entities.User;
import project.library.entities.dto.UserDto;
import project.library.enums.ExceptionMessages;
import project.library.exceptions.LoginException;
import project.library.exceptions.RegistrationException;
import project.library.mapper.Mapper;
import project.library.validator.Validator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    private RoleDao roleDao;

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

    public void createTestUsers() {
        User userAdmin = new User("sdasd@asd.com", "asd", "asd");
        Role roleAdmin = new Role("ROLE_ADMIN");
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(roleAdmin);
        userAdmin.setRole(roleAdmin);

        Role roleUser = new Role("ROLE_USER");
        User userUser = new User("sdasd1@asd.com", "asda", "asd");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);
        userUser.setRole(roleUser);

        userDao.save(userUser);
        userDao.save(userAdmin);

        User userAdmin1 = new User("sda1sd@asd.com", "asd1", "asd1");
        Role roleAdmin1 = new Role("ROLE_ADMIN1");
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(roleAdmin);


        Role roleUser1 = new Role("ROLE_USER1");
        User userUser1 = new User("sda1sd1@asd.com", "asda1", "asd1");
        Set<Role> userRoles1 = new HashSet<>();
        userRoles1.add(roleUser1);

        roleDao.save(roleAdmin1);
        roleDao.save(roleUser1);
        userDao.save(userUser1);
        userDao.save(userAdmin1);
        Collection


    }
}
