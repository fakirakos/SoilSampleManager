package com.example.UserActivities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpValidation {

    private String username;
    private String email;
    private String password;
    private String verifyPassword;


    public SignUpValidation(String email, String password, String verifyPassword) {

        this.email = email;
        this.password = password;
        this.verifyPassword = verifyPassword;
    }

    public boolean isEmailValid() {
        return isEmailNotEmpty() && isEmailLengthEnough()
                && emailContainsDot() && emailContainsDuck()
                && emailPatternIsMatched();
    }

    public boolean isEmailNotEmpty() {
        return !email.isEmpty();
    }

    public boolean isEmailLengthEnough() {
        return email.length() >= 8;
    }

    public boolean emailContainsDot() {
        return email.contains(".");
    }

    public boolean emailContainsDuck() {
        return email.contains("@");
    }

    public boolean emailPatternIsMatched() {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Password verification

    public boolean isPasswordValid() {
        return isPasswordNotEmpty() && isPasswordLengthEnough();
    }

    public boolean isPasswordNotEmpty() {
        return !password.isEmpty();
    }

    public boolean isPasswordLengthEnough() {
        return password.length() >= 6;
    }


    //Check if verifyPassword is valid

    public boolean isVerifyPasswordValid() {
        return arePasswordAndVerifyTheSame() && isVerifyNotEmpty();
    }

    public boolean arePasswordAndVerifyTheSame() {
        return password.equals(verifyPassword);
    }

    public boolean isVerifyNotEmpty() {
        return !verifyPassword.isEmpty();
    }
}
