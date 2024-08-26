package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> getNotes();

    Note getNote(Integer id, Integer userId);

    boolean insert(Note note, int userId);

    boolean update(Note note, int userId);
}
