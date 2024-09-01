package com.udacity.jwdnd.course1.cloudstorage.enums;

public enum NOTE {
    CREATE_INTERNAL_ERROR("errorNotes", "There was an error during create note, please try again."),
    UPDATE_INTERNAL_ERROR("errorNotes", "There was an error when update note, please try again."),
    DELETE_INTERNAL_ERROR("errorNotes", "There was an error when delete note, please try again."),
    NOT_FOUND_ERROR("errorNotes", "Note not found, please try again"),
    CREATE_SUCCESS("successNote", "Add Successfully"),
    UPDATE_SUCCESS("successNote", "Update Successfully"),
    DELETE_SUCCESS("successNote", "Delete Successfully");

    private final String variable;
    private final String message;

    NOTE(String variable, String message) {
        this.variable = variable;
        this.message = message;
    }

    public String getVariable() {
        return variable;
    }

    public String getMessage() {
        return message;
    }
}
