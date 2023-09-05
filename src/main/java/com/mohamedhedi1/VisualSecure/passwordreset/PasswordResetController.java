package com.mohamedhedi1.VisualSecure.passwordreset;

import com.mohamedhedi1.VisualSecure.user.User;
import com.mohamedhedi1.VisualSecure.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/password-reset")
@CrossOrigin(origins = "*")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam("email") String email) throws MessagingException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }else {
            String token = UUID.randomUUID().toString();
            passwordResetService.createPasswordResetTokenForUser(user.get(), token);
            passwordResetService.sendPasswordResetEmail(email, token);

            return ResponseEntity.ok("Password reset email sent successfully");

        }

     }

    @PutMapping("/reset")
    public ResponseEntity<PasswordResetResponse> resetPassword(@RequestParam("token") String token, @RequestParam("password") String newPassword) {
        return ResponseEntity.ok(passwordResetService.resetPassword(token,newPassword));
    }
}
