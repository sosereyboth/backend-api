package com.sosereyboth.backendapi.controller;

import com.sosereyboth.backendapi.dto.UpdateReserveDto;
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

@RestController
@RequestMapping("/reserve")
public class ReservedBooksController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public List<Book> getReservedBooks(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        return bookRepository.findByReservedBy(username);

    }

    @PostMapping
    public ResponseEntity<Book> reserveBook(@RequestBody UpdateReserveDto updateReserve, HttpServletRequest request) {
        try {
            Optional<Book> book = bookRepository.findById(updateReserve.getBookId());

            if (book.isPresent()) {
                if (updateReserve.isReserved()) {
                    String authHeader = request.getHeader("Authorization");
                    String token = authHeader.substring(7);
                    String username = jwtUtil.extractUsername(token);
                    book.get().setReservedBy(username);
                }else {
                    book.get().setReservedBy(null);
                }

                Book updatedBook = bookRepository.save(book.get());
                return new ResponseEntity<>(updatedBook, HttpStatus.OK);

            }
        } catch (Exception ex) {
            return new ResponseEntity<>((Book) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>((Book) null, HttpStatus.BAD_REQUEST);
    }


}
