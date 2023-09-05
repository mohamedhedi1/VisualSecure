package com.mohamedhedi1.VisualSecure.passwordreset;

import com.mohamedhedi1.VisualSecure.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);



}
