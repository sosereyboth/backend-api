package com.sosereyboth.backendapi.repository;

import com.sosereyboth.backendapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    public List<Book> findByReservedBy(String reservedBy);

}
