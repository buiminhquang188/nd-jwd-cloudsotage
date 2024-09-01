package com.udacity.jwdnd.course1.cloudstorage.enums;

public enum CREDENTIAL {
    CREATE_INTERNAL_ERROR("errorCredential", "There was an error during create credential, please try again."),
    UPDATE_INTERNAL_ERROR("errorCredential", "There was an error during update credential, please try again."),
    DELETE_INTERNAL_ERROR("errorCredential", "There was an error during delete credential, please try again."),
    NOT_FOUND_ERROR("errorCredential", "Credential not found, please try again"),
    CREATE_SUCCESS("successCredential", "Add Successfully"),
    UPDATE_SUCCESS("successCredential", "Update Successfully"),
    DELETE_SUCCESS("successCredential", "Delete Successfully");

    CREDENTIAL(String variable, String message) {
        this.variable = variable;
        this.message = message;
    }

    private final String variable;
    private final String message;

    public String getVariable() {
        return variable;
    }

    public String getMessage() {
        return message;
    }
}
