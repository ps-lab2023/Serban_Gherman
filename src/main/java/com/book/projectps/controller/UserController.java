package com.book.projectps.controller;

import com.book.projectps.exceptions.UserAlreadyExistsException;
import com.book.projectps.exceptions.UserNotFoundException;
import com.book.projectps.model.RegisterRequest;
import com.book.projectps.model.User;
import com.book.projectps.service.impl.EmailServiceImpl;
import com.book.projectps.service.impl.UserServiceImpl;



import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmailServiceImpl emailSenderService;
    private static final String RECAPTCHA_SECRET_KEY = "6Lc49QAmAAAAAKksQo9iExOs_I1EJPW8aCcWd-zi";

    @Autowired
    private RestTemplate restTemplate;
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    // ... other methods

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
    @GetMapping("/login")
       public ResponseEntity<User> findByName(@RequestBody String displayName, String password) {

       User user = userService.findByUsernameAndPassword(displayName,password);
       return user !=null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> Register(@RequestBody RegisterRequest registerRequest) {
        // Validate the reCAPTCHA
        String reCaptchaResponse = registerRequest.getCaptchaResponse();
        String url = "https://www.google.com/recaptcha/api/siteverify";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("secret", RECAPTCHA_SECRET_KEY);
        map.add("response", reCaptchaResponse);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // Check if the captcha validation was successful
        if (!responseEntity.getBody().contains("\"success\": true")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        System.out.println(registerRequest.getRole());
        System.out.println(registerRequest.getDisplayName());
        User user = new User();
        user.setDisplayName(registerRequest.getDisplayName());
        user.setUsername(registerRequest.getDisplayName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setRole(registerRequest.getRole());
        user.setLogged(false);

        try {
            User savedUser = userService.register(user);
            System.out.println("try catch register");
            emailSenderService.sendMailWithAttachment(registerRequest.getEmail(),
                    "Welcome to our website!\n Thank you for registering '" + registerRequest.getDisplayName() +"' .",
                    "FreeArtists Registration");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.save1(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping ("/login")
    public ResponseEntity<User> logIn(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println(username);
        System.out.println(password);
        User user = userService.findByUsernameAndPassword(username, password);
        if (user != null) {
            user.setLogged(true);
            userService.login(user);


            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

}
