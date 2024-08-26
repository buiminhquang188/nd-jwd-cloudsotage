package com.udacity.jwdnd.course1.cloudstorage.services.impl;

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
    public List<Note> getNotes() {
        return this.noteMapper.getNotes();
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
    public boolean update(Note note, int userId) {
        note.setUserId(userId);
        return this.noteMapper.update(note) > 0;
    }
}
