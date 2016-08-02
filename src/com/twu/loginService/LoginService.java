package com.twu.loginService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vanbujm on 2/08/2016.
 * Mock Login Service
 */
public class LoginService {

    private Boolean isLoggedIn = false;

    public Boolean login(String user, String password) {
        Pattern pattern = Pattern.compile( "\\d\\d\\d-\\d\\d\\d\\d");
        Matcher m = pattern.matcher(user);
        Boolean result = m.find();
        isLoggedIn = result;
        return result;
    }

    public String getName() {
        return isLoggedIn? "Jane" : "Unauthorized";
    }

    public String getEmail() {
        return isLoggedIn? "jane@email.com" : "Unauthorized";
    }

    public String getPhoneNumber() {
        return isLoggedIn? "0000 000 000" : "Unauthorized";
    }

}
