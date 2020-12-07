package com.codegym.service;

import com.codegym.model.Note;
import com.codegym.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {
    Page<Note> findAll(Pageable pageable);
//    Page<Note> findAllByUser (Pageable pageable, User user);
    Note findById(Long id);
    void save(Note note);
    void delete(Long id);
    Note start(Long id);
    void setStatus(Note note);
}
