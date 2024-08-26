package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> getNotes();

    Note getNote(Integer id, Integer userId);

    boolean insert(Note note, int userId);

    Response update(Note note, int userId);

    Response delete(int noteId, int userId);
}
