package com.codegym.service.impl;

import com.codegym.model.Note;
import com.codegym.repository.NoteRepository;
import com.codegym.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    @PreAuthorize("isAuthenticated()")
    public Page<Note> findAll(Pageable pageable) {
        Page<Note> notes = noteRepository.findAll(pageable);
        for (Note note: notes) {
            if (note.getStatus().equals("Running")){
                if (note.getEndTime().compareTo(LocalDateTime.now())<0){
                    note.setStatus("Finished");
                    noteRepository.save(note);
                }
            }
        }
        return notes;
    }

    @Override
    @PreAuthorize("isAuthenticated() and (@noteRepository.findOne(#id).user.username==principal?.username)")
    public Note findById(Long id){
        Note note = noteRepository.findOne(id);
        if (note.getStatus().equals("Running")){
            if (note.getEndTime().compareTo(LocalDateTime.now())<0){
                note.setStatus("Finished");
                noteRepository.save(note);
            }
        }
        return note;
    }

    @Override
    @PreAuthorize("isAuthenticated() and ((#note.id==null) or (@noteRepository.findOne(#note.id).user.username==principal?.username))")
    public void save(Note note){
        noteRepository.save(note);
    }

    @Override
    @PreAuthorize("isAuthenticated() and (@noteRepository.findOne(#id).user.username==principal?.username)")
    public void delete(Long id){
        noteRepository.delete(id);
    }

    @Override
    @PreAuthorize("isAuthenticated() and (@noteRepository.findOne(#id).user.username==principal?.username)")
    public Note start(Long id){
        Note note = noteRepository.findOne(id);
        setStatus(note);
        return note;
    }

    @Override
    @PreAuthorize("isAuthenticated() and (@noteRepository.findOne(#note.id).user.username==principal?.username)")
    public void setStatus(Note note){
        if(note.getStatus().equals("Not running")){
            note.setStartTime(LocalDateTime.now());
            note.setEndTime(LocalDateTime.now().plusMinutes(note.getEstimatedFinishedTime()));
            note.setStatus("Running");
            noteRepository.save(note);
        }
        else if (note.getStatus().equals("Running")){
            if (note.getEndTime().compareTo(LocalDateTime.now())<0){
                note.setStatus("Finished");
                noteRepository.save(note);
            }
        } else {
            //
        }
    }
}
