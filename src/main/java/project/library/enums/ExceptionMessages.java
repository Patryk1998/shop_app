package project.library.enums;


import org.springframework.stereotype.Component;

@Component
public class ExceptionMessages {
    public String EMAIL_EXIST = "Another account is already assigned to this email! Try enter other email.";
    public String EMAIL_WRONG_FORM = "Entered email is wrong. Check form!";
    public String USERNAME_EXIST = "Another account has the same username! Try add some numbers.";
    public String PASSWORDS_NOT_EQUALS = "Two different passwords. Wrong confirmation!";
    public String USERNAME_NOT_EXIST = "Entered username does not exist!";
    public String PASSWORD_NOT_CORRECT = "Entered password is wrong.";
}
