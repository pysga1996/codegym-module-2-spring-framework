package com.codegym.repository;

import com.codegym.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    @Query("select n from Note n where n.user.username = ?#{ principal?.username }")
    Page<Note> findAll(Pageable pageable);
}
