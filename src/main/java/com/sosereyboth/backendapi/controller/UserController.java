package com.sosereyboth.backendapi.controller;

import com.sosereyboth.backendapi.entity.Book;
import com.sosereyboth.backendapi.entity.User;
import com.sosereyboth.backendapi.repository.UserRepository;
import com.sosereyboth.backendapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;
@Autowired
    private JwtUtil jwtUtil;

//    @GetMapping
//    public List<User> getUsers(){
//        return repository.findAll();
//    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        try {
            Optional<User> found = repository.findById(user.getId());
            if (found.isPresent()) {

                found.get().setFullName(user.getFullName());
                found.get().setUserName(user.getUserName());
                found.get().setEmail(user.getEmail());
                found.get().setPhone(user.getPhone());
                User saved = repository.save(found.get());

                return new ResponseEntity<User>(saved, HttpStatus.OK);
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
        return new ResponseEntity<User>((User) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<User> getUser(  HttpServletRequest request){
        String authHeader =request.getHeader("Authorization") ;
        String token =authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        return new ResponseEntity<User>( repository.findByUserName(username), HttpStatus.OK);

    }

}
