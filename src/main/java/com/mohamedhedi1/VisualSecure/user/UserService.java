package com.mohamedhedi1.VisualSecure.user;

import com.mohamedhedi1.VisualSecure.exception.UserAlreadyExistsException;
import com.mohamedhedi1.VisualSecure.exception.UserNotFoundException;
import com.mohamedhedi1.VisualSecure.permission.Permission;
import com.mohamedhedi1.VisualSecure.permission.PermissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;

    @Override
    public User add(User user) {
        Optional<User> theUser = userRepository.findByEmail(user.getEmail());
        if (theUser.isPresent()){
            throw new UserAlreadyExistsException("A user with " +user.getEmail() +" already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<UserRecord> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserRecord(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        new HashSet<>(user.getPermissions()))).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User update(User user) {
        user.setPermissions(user.getPermissions());
        return userRepository.save(user);
    }

    @Override
    public User getUserDetails(Long id) {
        User user =userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        System.out.println(user.getAuthorities());
        return user;

    }
}