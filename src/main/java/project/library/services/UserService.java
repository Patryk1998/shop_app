package project.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import project.library.dao.*;
import project.library.entities.library.Piece;
import project.library.entities.library.Rent;
import project.library.entities.library.Title;
import project.library.entities.library.Type;
import project.library.entities.login.Role;
import project.library.entities.login.User;
import project.library.entities.dto.UserDto;
import project.library.enums.ExceptionMessages;
import project.library.exceptions.LoginException;
import project.library.exceptions.RegistrationException;
import project.library.mapper.Mapper;
import project.library.validator.Validator;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RentDao rentDao;

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private PieceDao pieceDao;

    public boolean addNewAccount(UserDto userDto) throws RegistrationException {

        if(validator.validate(userDto.getEmail(), validator.EMAIL_PATTERN)) throw new RegistrationException(exceptionMessages.EMAIL_WRONG_FORM);
        if(checkIfEmailExist(userDto.getEmail())) throw new RegistrationException(exceptionMessages.EMAIL_EXIST);
        if(checkIfUsernameExist(userDto.getUsername())) throw new RegistrationException(exceptionMessages.USERNAME_EXIST);
        if(checkIfPasswordsSame(userDto)) throw new RegistrationException(exceptionMessages.PASSWORDS_NOT_EQUALS);

        User user = mapper.userDtoToUser(userDto);
        Role role = checkIfRoleExist("ROLE_USER");
        user.setRole(role);
        role.setUser(user);

        User newUser = userDao.save(user);

        if(newUser.getEmail().equals(userDto.getEmail()) &&
                newUser.getUsername().equals(userDto.getUsername()) && newUser.getUsername().equals(userDto.getUsername())) {
            return true;
        } else return false;
    }

        //jeżeli hasła są takie same to zwraca false tak aby nie został rzucony exception w metodzie wywołującej
    public boolean checkIfPasswordsSame(UserDto userDto) {
        if(userDto.getPassword().equals(userDto.getPasswordConfirm())) return false;
        else return true;
    }

    public User getUserById(Long id) {
        return userDao.findById(id).get();
    }

    public Iterable<User> getUsers() {
        return userDao.findAll();
    }

    public void changeUser(Long id, UserDto userDto) {
        User user = userDao.findById(id).get();
        if(!user.getEmail().equals(userDto.getEmail())) user.setEmail(userDto.getEmail());
        if(!user.getName().equals(userDto.getName())) user.setName(userDto.getName());
        if(!user.getSurname().equals(userDto.getSurname())) user.setSurname(userDto.getSurname());
        if(!user.getUsername().equals(userDto.getUsername())) user.setUsername(userDto.getUsername());
        if(!user.getPassword().equals(userDto.getPassword())) user.setPassword(userDto.getPassword());

        userDao.save(user);
    }

    public void deleteUserById(Long id) {
        userDao.delete(userDao.findById(id).get());
    }

    public boolean checkIfUsernameExist(String username) {
        return userDao.existsByUsername(username);
    }

    public boolean checkIfEmailExist(String email) {
        return userDao.existsByEmail(email);
    }

    public Role checkIfRoleExist(String role) {
        return roleDao.findByRole(role).orElse(new Role(role));
    }

    public void getUserIdFromSession(HttpServletRequest request) {
        UsernamePasswordAuthenticationToken asd = (UsernamePasswordAuthenticationToken) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        MyUserDetails userDetails = (MyUserDetails) asd.getDetails();
    }

    public void createTestUsers() {
        User userAdmin = new User("sdasd@asd.com", "asd", "asd", "asd", "asd");
        Role roleAdmin = new Role("ROLE_ADMIN");
        userAdmin.setRole(roleAdmin);

//        Role roleUser = new Role("ROLE_USER");
//        User userUser = new User("sdasd1@asd.com", "asd1", "asd", "asda", "asd");
//        userUser.setRole(roleUser);
//
//
        Title title1 = new Title("title1", "author1", 2000);
        Title title2 = new Title("title2", "author2", 2000);
        Title title3 = new Title("title3", "author3", 2000);

        Type type = new Type("asdasdasdasd");
        title1.setType(type);
        title2.setType(type);
        title3.setType(type);
        type.setTitle(title1);
        type.setTitle(title2);
        type.setTitle(title3);

        Piece piece1 = new Piece(true);
        Piece piece2 = new Piece(true);
        Piece piece3 = new Piece(true);
        Piece piece4 = new Piece(true);
        Piece piece5 = new Piece(true);
        Piece piece6 = new Piece(true);
        Piece piece7 = new Piece(true);

        piece1.setTitle(title1);
        piece2.setTitle(title1);
        piece3.setTitle(title2);
        piece4.setTitle(title2);
        piece5.setTitle(title3);
        piece6.setTitle(title3);
        piece7.setTitle(title3);
//
//        Rent rent1 = new Rent();
//        Rent rent2 = new Rent();
//
//        piece1.setRent(rent1);
//        piece2.setRent(rent1);
//        piece3.setRent(rent1);
//        piece4.setRent(rent2);
//        piece5.setRent(rent2);
//        piece6.setRent(rent2);
//        piece7.setRent(rent2);
//
//        rent1.setUser(userUser);
//        rent2.setUser(userAdmin);


        pieceDao.save(piece1);
        pieceDao.save(piece2);
        pieceDao.save(piece3);
        pieceDao.save(piece4);
        pieceDao.save(piece5);
        pieceDao.save(piece6);
        pieceDao.save(piece7);
//        rentDao.save(rent1);
//        rentDao.save(rent2);
        titleDao.save(title1);
        titleDao.save(title2);
        titleDao.save(title3);
//        userDao.save(userUser);
        Iterable<User> users1 = userDao.findAll();
        User user = userDao.save(userAdmin);
        Iterable<User> users2 = userDao.findAll();


    }
}
