package com.codegym.controller.api;

import com.codegym.model.Note;
import com.codegym.model.User;
import com.codegym.service.NoteService;
import com.codegym.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/user/{userId}/note")
@RestController
public class NoteRestController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @ModelAttribute
    public User user() {
        return userDetailService.getCurrentUser();
    }

    @GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE  })
    public ResponseEntity<Page<Note>> getNoteList(Pageable pageable) {
        Page<Note> noteList = noteService.findAll(pageable);
        if (noteList.getTotalElements()==0) {
            return new ResponseEntity<Page<Note>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Page<Note>>(noteList, HttpStatus.OK);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> getNote(@PathVariable("id") long id) {
        Note note = noteService.findById(id);
        if (note == null) {
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Note>(note, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNote(@RequestBody Note note, UriComponentsBuilder ucBuilder) {
        noteService.save(note);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/view/{id}").buildAndExpand(note.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> editNote(@PathVariable("id") Long id, @RequestBody Note note) {
        Note currentNote = noteService.findById(id);
        if (currentNote == null) {
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }
        currentNote.setTitle(note.getTitle());
        currentNote.setCategories(note.getCategories());
        currentNote.setContent(note.getContent());
        currentNote.setId(note.getId());
        noteService.save(currentNote);
        return new ResponseEntity<Note>(currentNote, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> deleteCustomer(@PathVariable("id") long id) {
        Note note = noteService.findById(id);
        if (note == null) {
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }
        noteService.delete(id);
        return new ResponseEntity<Note>(HttpStatus.NO_CONTENT);
    }

}
