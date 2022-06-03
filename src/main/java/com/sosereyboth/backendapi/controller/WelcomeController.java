package com.sosereyboth.backendapi.controller;

import com.sosereyboth.backendapi.entity.AuthRequest;
import com.sosereyboth.backendapi.entity.User;
import com.sosereyboth.backendapi.repository.UserRepository;
import com.sosereyboth.backendapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        //Validate username and password.
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username or password.");
        }

        //Generate a web token when succeeded.
        return jwtUtil.generateToken(authRequest.getUserName());

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User request) {
        User user = repository.findByUserName(request.getUserName());
        if (user != null) {
            return new ResponseEntity<>("This username is already token.", HttpStatus.BAD_REQUEST);
        }
        if (request.getPassword().trim().isEmpty()) {
            return new ResponseEntity<>("Please enter your password.", HttpStatus.BAD_REQUEST);
        }

        try {
            String newId = UUID.randomUUID().toString();
            User newUser = new User(newId, request.getUserName(), request.getPassword(), request.getEmail(),
                    request.getPhone(), request.getPhoto());
            repository.save(newUser);
        }catch (Exception ex){
            return new ResponseEntity<>("Register failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>("Register succeeded.", HttpStatus.OK);
    }
}
