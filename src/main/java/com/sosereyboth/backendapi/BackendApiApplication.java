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
                new User("101", "user 1","user1", "pwd1", "user1@gmail.com", "011 111 222", ""),
                new User("102","user 2", "user2", "pwd2", "user2@gmail.com", "012 222 333", ""),
                new User("103", "user 3","user3", "pwd3", "user3@gmail.com", "013 333 444", "")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);

        List<Book> books = Stream.of(
                new Book("1", "James Joyce", "Ulysses", "Fiction", "Ulysses chronicles the passage of Leopold Bloom through Dublin during an ordinary day, June 16, 1904.", ""),
                new Book("2", "Miguel de Cervantes", "Don Quixote", "Fiction", "Alonso Quixano, a retired country gentleman in his fifties, lives in an unnamed section of La Mancha", null),
                new Book("3", "Gabriel Garcia Marquez", "One Hundred Years of Solitude", "Fiction", "One of the 20th century's enduring works, One Hundred Years of Solitude is a widely beloved and acclaimed novel known", null),
                new Book("4", "F. Scott Fitzgerald", "The Great Gatsby", "Fiction", "The novel chronicles an era that Fitzgerald himself dubbed the \"Jazz Age\". Following the shock and chaos of World War I", null),
                new Book("5", "Herman Melville", "Moby Dick", "Fiction", "First published in 1851, Melville's masterpiece is, in Elizabeth Hardwick's words, \"the greatest novel in American literature.\"", null),
                new Book("6", "Leo Tolstoy", "War and Peace", "Fiction", "Epic in scale, War and Peace delineates in graphic detail events leading up to Napoleon's invasion of Russia, and the impact of the Napoleonic era on Tsarist society", null),
                new Book("7", "William Shakespeare", "Hamlet ", "Fiction", "The Tragedy of Hamlet, Prince of Denmark, or more simply Hamlet, is a tragedy by William Shakespeare, believed to have been written between 1599 and 1601. The play", null),
                new Book("8", "Homer", "The Odyssey", "Fiction", "The Odyssey is one of two major ancient Greek epic poems attributed to Homer. It is, in part, a sequel to the Iliad", null),
                new Book("9", "Gustave Flaubert", "Madame Bovary", "Fiction", "For daring to peer into the heart of an adulteress and enumerate its contents with profound dispassion, the author of Madame Bovary was tried for \"offenses against morality and religion.\"", null),
                new Book("10", "Dante Alighieri", "The Divine Comedy", "Fiction", "Belonging in the immortal company of the great works of literature, Dante Alighieri's poetic masterpiece, The Divine Comedy, is a moving human drama", null),
                new Book("11", "Fyodor Dostoyevsky", "The Brothers Karamazov", "Fiction", "Dostoevsky's last and greatest novel, The Karamazov Brothers, is both a brilliantly told crime story and a passionate philosophical debate.", null),
                new Book("12", "Fyodor Dostoyevsky", "Crime and Punishment", "Fiction", "It is a murder story, told from a murder;s point of view, that implicates even the most innocent reader in its enormities.", null),
                new Book("13", "Emily Brontë", "Wuthering Heights", "Fiction", "The narrative is non-linear, involving several flashbacks, and two primary narrators: Mr. Lockwood and Ellen \"Nelly\" Dean.", null),
                new Book("14", "J. D. Salinger", "The Catcher in the Rye", "Fiction", "The Catcher in the Rye is a 1945 novel by J. D. Salinger. Originally published for adults, the novel has become a common part of high school and college curricula throughout the English-speaking", null),
                new Book("15", "Jane Austen", "Pride and Prejudice", "Fiction", "The book is narrated in free indirect speech following the main character Elizabeth Bennet as she deals with matters of upbringing", null)
        ).collect(Collectors.toList());
        bookRepository.saveAll(books);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }

}
