package com.sosereyboth.backendapi.repository;

import com.sosereyboth.backendapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
