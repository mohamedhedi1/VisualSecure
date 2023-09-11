package com.mohamedhedi1.VisualSecure.user;

import com.mohamedhedi1.VisualSecure.permission.Permission;
import java.util.Set;

public record UserRecord(Long id, String firstName, String lastName, String email, Set<Permission> permissions){}