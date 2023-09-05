package com.mohamedhedi1.VisualSecure.user;

import com.mohamedhedi1.VisualSecure.auth.AuthenticationResponse;
import com.mohamedhedi1.VisualSecure.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(
    ) {
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
