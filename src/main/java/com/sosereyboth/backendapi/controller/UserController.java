package com.sosereyboth.backendapi.controller;

import com.sosereyboth.backendapi.entity.Book;
import com.sosereyboth.backendapi.entity.User;
import com.sosereyboth.backendapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getUsers(){
        return repository.findAll();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        try {
            Optional<User> found = repository.findById(user.getId());
            if (found.isPresent()) {
                return new ResponseEntity<User>(repository.save(user), HttpStatus.OK);
            }

        }catch (Exception ex){

        }
        return new ResponseEntity<User>((User) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
