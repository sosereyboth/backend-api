package com.sosereyboth.backendapi;

import com.sosereyboth.backendapi.entity.Book;
import com.sosereyboth.backendapi.entity.User;
import com.sosereyboth.backendapi.repository.BookRepository;
import com.sosereyboth.backendapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApiApplication {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User("101", "user1", "pwd1", "user1@gmail.com","011 111 222", ""),
                new User("102", "user2", "pwd2", "user2@gmail.com", "012 222 333",""),
                new User("103", "user3", "pwd3", "user3@gmail.com", "013 333 444", null)
        ).collect(Collectors.toList());
        userRepository.saveAll(users);

        List<Book> books = Stream.of(
                new Book("001",  "James Joyce", "Ulysses","Fiction","Ulysses chronicles the passage of Leopold Bloom through Dublin during an ordinary day, June 16, 1904."),
                new Book("002",  "Miguel de Cervantes", "Don Quixote","Fiction","Alonso Quixano, a retired country gentleman in his fifties, lives in an unnamed section of La Mancha"),
                new Book("003",  "Gabriel Garcia Marquez", " One Hundred Years of Solitude","Fiction","One of the 20th century's enduring works, One Hundred Years of Solitude is a widely beloved and acclaimed novel known")

        ).collect(Collectors.toList());
        bookRepository.saveAll(books);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }

}
