package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.enums.NOTE;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @Override
    public List<Note> getNotes(int userId) {
        return this.noteMapper.getNotes(userId);
    }

    @Override
    public Note getNote(Integer id, Integer userId) {
        return this.noteMapper.getNote(id, userId);
    }

    @Override
    public boolean insert(Note note, int userId) {
        note.setUserId(userId);
        return this.noteMapper.insert(note) > 0;
    }

    @Override
    public Response update(Note note, int userId) {
        Note currentNote = this.getNote(note.getId(), userId);
        if (currentNote == null) {
            return new Response(NOTE.NOT_FOUND_ERROR.getMessage(), false);
        }

        note.setUserId(userId);
        int resultIndex = this.noteMapper.update(note);

        if (resultIndex < 0) {
            return new Response(NOTE.UPDATE_INTERNAL_ERROR.getMessage(), false);
        }

        return new Response(
                NOTE.UPDATE_SUCCESS.getMessage(),
                true
        );
    }

    @Override
    public Response delete(int noteId, int userId) {
        Note note = this.getNote(noteId, userId);
        if (note == null) {
            return new Response(NOTE.NOT_FOUND_ERROR.getMessage(), false);
        }

        int resultIndex = this.noteMapper.delete(noteId, userId);
        if (resultIndex < 0) {
            return new Response(NOTE.DELETE_INTERNAL_ERROR.getMessage(), false);
        }

        return new Response(
                NOTE.DELETE_SUCCESS.getMessage(),
                true
        );
    }
}
