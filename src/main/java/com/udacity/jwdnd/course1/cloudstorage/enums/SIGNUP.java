package com.udacity.jwdnd.course1.cloudstorage.enums;

public enum SIGNUP {
    USER_EXIST_ERROR("signupError", "The username is already exists."),
    INTERNAL_ERROR("signupError", "There was an error signing you up. Please try again."),
    SUCCESS("signupSuccess", true);
    private String string;
    private Object object;

    SIGNUP(String string, Object object) {
        this.string = string;
        this.object = object;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
