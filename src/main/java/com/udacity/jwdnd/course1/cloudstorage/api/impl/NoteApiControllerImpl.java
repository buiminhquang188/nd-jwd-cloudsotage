package com.udacity.jwdnd.course1.cloudstorage.api.impl;

import com.udacity.jwdnd.course1.cloudstorage.api.NoteApiController;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.response.BaseResponse;
import com.udacity.jwdnd.course1.cloudstorage.response.NoteResponse;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class NoteApiControllerImpl implements NoteApiController {
    private final NoteService noteService;

    public NoteApiControllerImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public ResponseEntity<BaseResponse<NoteResponse>> getNote(Integer id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Note note = this.noteService.getNote(id, user.getId());

        if (note == null) return new ResponseEntity<>(
                new BaseResponse<>(
                        HttpStatus.NOT_FOUND.getReasonPhrase() + " Notes. Please try again!",
                        HttpStatus.NOT_FOUND.value(),
                        null
                ),
                HttpStatus.NOT_FOUND
        );

        NoteResponse noteResponse = new NoteResponse(
                note.getId(),
                note.getNoteTitle(),
                note.getNoteDescription()
        );

        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.getReasonPhrase(),
                        HttpStatus.OK.value(),
                        noteResponse
                )
        );
    }
}
