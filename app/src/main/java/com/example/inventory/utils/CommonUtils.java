package com.example.inventory.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonUtils {
    static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&?;\\-_])(?!.*\\s).{8,20}$";

    public static boolean isPasswordValid(String password){
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }

}
