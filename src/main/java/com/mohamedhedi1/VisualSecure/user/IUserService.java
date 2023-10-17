package com.mohamedhedi1.VisualSecure.user;

import java.util.List;

public interface IUserService {
    User add(User user);
    List<User> getAllUsers();
    void delete(String email);
    User getUser(String email);
    User update(User user);

    User getUserDetails(Long id);
}