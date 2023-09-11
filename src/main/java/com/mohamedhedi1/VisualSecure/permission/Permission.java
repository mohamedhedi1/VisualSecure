package com.mohamedhedi1.VisualSecure.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mohamedhedi1.VisualSecure.role.Role;
import com.mohamedhedi1.VisualSecure.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Collection<User> users = new HashSet<>();

    public Permission(String name) {
        this.name = name;
    }

    public void removeAllUsersFromPermission() {
        if (this.getUsers() != null){
            List<User> usersInRole = this.getUsers().stream().toList();
            usersInRole.forEach(this::removeUserFromPermission);
        }
    }
    public void removeUserFromPermission(User user) {
        user.getPermissions().remove(this);
        this.getUsers().remove(user);
    }
    public void assignUserToPermission(User user){
        user.getPermissions().add(this);
        this.getUsers().add(user);
    }


}
