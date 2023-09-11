package com.mohamedhedi1.VisualSecure.permission;

import com.mohamedhedi1.VisualSecure.exception.PermissionAlreadyExistException;
import com.mohamedhedi1.VisualSecure.exception.UserAlreadyExistsException;
import com.mohamedhedi1.VisualSecure.exception.UserNotFoundException;
import com.mohamedhedi1.VisualSecure.user.User;
import com.mohamedhedi1.VisualSecure.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService{
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;



    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission createPermission(Permission permission) {
        Optional<Permission> checkPermission  = permissionRepository.findByName(permission.getName());
        if (checkPermission.isPresent()){
            throw new PermissionAlreadyExistException(checkPermission.get().getName()+ "permission already exist");
        }
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        this.removeAllUserFromPermission(permissionId);
        permissionRepository.deleteById(permissionId);
    }

    @Override
    public Permission removeAllUserFromPermission(Long permissionId) {
        Optional<Permission> permission = permissionRepository.findById(permissionId);
        permission.ifPresent(Permission::removeAllUsersFromPermission);
        return permissionRepository.save(permission.get());
    }

    @Override
    public User removeUserFromPermission(Long userId, Long permissionId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Permission> permission = permissionRepository.findById(permissionId);
        if (permission.isPresent() && permission.get().getUsers().contains(user.get())) {
            permission.get().removeUserFromPermission(user.get());
            permissionRepository.save(permission.get());
            return user.get();
        }
        throw new UserNotFoundException("User not found!");
    }

    @Override
    public User assignUerToPermission(Long userId, Long permissionId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Permission> permission = permissionRepository.findById(permissionId);
        if (user.isPresent() && user.get().getPermissions().contains(permission.get())){
            throw new UserAlreadyExistsException(
                    user.get().getFirstName()+ " is already assigned to the " + permission.get().getName() +" permission");
        }
        permission.ifPresent(thePermission -> thePermission.assignUserToPermission(user.get()));
        permissionRepository.save(permission.get());
        return user.get();
    }

    @Override
    public Permission findByName(String name) {
        return permissionRepository.findByName(name).get();
    }

    @Override
    public Permission findById(Long permissionId) {
        return permissionRepository.findById(permissionId).get();
    }
}
