package com.codegym.controller.common;

import com.codegym.model.Category;
import com.codegym.model.Note;
import com.codegym.model.User;
import com.codegym.service.CategoryService;
import com.codegym.service.NoteService;
import com.codegym.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @ModelAttribute("user")
    public User user() {
        return userDetailService.getCurrentUser();
    }

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping(value = "", params = "action=list")
    public ModelAndView list(Pageable pageable) {
        Page<Note> notes = noteService.findAll(pageable);
        return new ModelAndView("/note/list", "notes", notes);
    }

    @GetMapping(value = "", params = "id")
    public ModelAndView readNote(Long id) {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/view");
        modelAndView.addObject("note", note);
        return modelAndView;
    }


    @GetMapping(value = "", params = "action=create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }

    @PostMapping(value = "", params = "action=create")
    public ModelAndView saveNote(@ModelAttribute("note") Note note) {
        note.setUser(user());
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("message", "New note created successfully");
        return modelAndView;
    }


    @GetMapping(value = "", params = {"action=edit", "id"})
    public ModelAndView showEditForm(Long id) {
        Note note = noteService.findById(id);
        if (note != null) {
            ModelAndView modelAndView = new ModelAndView("/note/edit");
//            User user = userDetailService.getCurrentUser();
            modelAndView.addObject("note", note);
//            modelAndView.addObject("user", user);
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping(value = "", params = {"action=edit"})
    public ModelAndView updateNote(@ModelAttribute("note") Note note) {
        note.setUser(user());
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note", note);
        modelAndView.addObject("message", "Note updated successfully");
        return modelAndView;
    }

    @GetMapping(value = "", params = {"action=delete", "id"})
    public ModelAndView showDeleteForm(@RequestParam("id") Long id) {
        Note note = noteService.findById(id);
        if (note != null) {
            ModelAndView modelAndView = new ModelAndView("/note/delete");
//            modelAndView.addObject("user", user);
            modelAndView.addObject("note", note);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping(value = "", params = "action=delete")
    public String deleteNote(@ModelAttribute("note") Note note) {
        noteService.delete(note.getId());
        return "redirect:/note?action=list";
    }

    @GetMapping(value = "", params = {"action=start", "id"})
    public ModelAndView startNote(@RequestParam("id") Long id) {
        Note note = noteService.start(id);
        ModelAndView modelAndView = new ModelAndView("/note/view");
        modelAndView.addObject("note", note);
        return modelAndView;
    }
}
