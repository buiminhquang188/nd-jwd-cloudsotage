package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping
public interface NoteController {
    @GetMapping("/notes")
    String getNotes(RedirectAttributes redirectAttributes);

    @PostMapping("/note")
    String addNotes(@ModelAttribute Note note, RedirectAttributes redirectAttributes, Authentication authentication);

    @PatchMapping("/note/{id}")
    String updateNote(@PathVariable("id") Integer id, @ModelAttribute Note note, RedirectAttributes redirectAttributes, Authentication authentication);
}
