package project.library.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {

    private Pattern pattern;
    private Matcher matcher;
    public final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]"+
        "(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    public boolean validate(String forValidation, String selectedPattern) {
        pattern = Pattern.compile(selectedPattern);
        matcher = pattern.matcher(forValidation);
        return !matcher.matches();
    }
}