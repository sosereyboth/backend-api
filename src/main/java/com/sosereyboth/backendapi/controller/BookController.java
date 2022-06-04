package com.sosereyboth.backendapi.controller;

import com.sosereyboth.backendapi.entity.Book;
import com.sosereyboth.backendapi.repository.BookRepository;
import com.sosereyboth.backendapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Book> registerBook(@RequestBody Book book){
        try {
            Book newBook =new Book(UUID.randomUUID().toString(), book.getAuthor(),
                    book.getTitle(), book.getCategory(), book.getDescription(), null);

            return new ResponseEntity<Book>(bookRepository.save(newBook), HttpStatus.OK);
        }catch (Exception ex){

        }
        return new ResponseEntity<Book>((Book) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeBook(@PathVariable String id){

        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return new ResponseEntity<String>( "Book deleted.",HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid book Id.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookDetail(@PathVariable String id){
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<Book>( book.get(),HttpStatus.OK);
        }

        //When book is not found.
        return new ResponseEntity<>((Book) null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        try {
            Optional<Book> found = bookRepository.findById(book.getId());
            if (found.isPresent()) {
                return new ResponseEntity<Book>(bookRepository.save(book), HttpStatus.OK);
            }

        }catch (Exception ex){

        }
        return new ResponseEntity<Book>((Book) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
