package com.udacity.jwdnd.course1.cloudstorage.enums;

public enum FILE {
    UPLOAD_SUCCESS("success", "Upload successfully"),
    DUPLICATE_NAME_ERROR("error", "The file is exist, please choose another file"),
    MISSING_ERROR("error", "Please choose file before hit upload button"),
    MAXIMUM_SIZE_ERROR("error", "The file is too large, please try with smaller size"),
    IO_ERROR("error", "There was an error during upload, please try again");

    private String variable;
    private String message;

    FILE(String variable, String message) {
        this.variable = variable;
        this.message = message;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
