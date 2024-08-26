package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.NoteController;
import com.udacity.jwdnd.course1.cloudstorage.enums.TABS;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NoteControllerImpl implements NoteController {
    private final NoteService noteService;

    public NoteControllerImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public String getNotes(RedirectAttributes redirectAttributes) {
        List<Note> notes = this.noteService.getNotes();
        redirectAttributes.addFlashAttribute("notes", notes);
        return "home";
    }

    @Override
    public String addNotes(Note note, RedirectAttributes redirectAttributes, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        boolean isInserted = this.noteService.insert(note, user.getId());

        redirectAttributes.addFlashAttribute("tab", TABS.NOTES);

        if (!isInserted) {
            String error = "There was an error during create note, please try again.";
            redirectAttributes.addFlashAttribute("errorNotes", error);
            redirectAttributes.addFlashAttribute("note", note);
            return "redirect:/home";
        }


        List<Note> notes = this.noteService.getNotes();
        redirectAttributes.addFlashAttribute("notes", notes);
        return "redirect:/home";
    }

    @Override
    public String updateNote(Integer id, Note note, RedirectAttributes redirectAttributes, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        note.setId(id);
        this.noteService.update(note, user.getId());

        List<Note> notes = this.noteService.getNotes();
        redirectAttributes.addFlashAttribute("notes", notes);
        redirectAttributes.addFlashAttribute("tab", TABS.NOTES);
        return "redirect:/home";
    }

    @Override
    public String deleteNode(Integer id, RedirectAttributes redirectAttributes, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        this.noteService.delete(id, user.getId());

        List<Note> notes = this.noteService.getNotes();
        redirectAttributes.addFlashAttribute("notes", notes);
        redirectAttributes.addFlashAttribute("tab", TABS.NOTES);

        return "redirect:/home";
    }
}
