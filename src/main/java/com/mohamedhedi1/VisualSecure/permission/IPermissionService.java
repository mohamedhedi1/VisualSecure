package com.mohamedhedi1.VisualSecure.permission;
import com.mohamedhedi1.VisualSecure.user.User;
import java.util.List;

public interface IPermissionService {
    List<Permission> getAllPermissions();

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);

    Permission removeAllUserFromPermission(Long permissionId);

    User removeUserFromPermission(Long userId, Long permissionId);

    User assignUerToPermission(Long userId, Long permissionId);

    Permission findByName(String name);

    Permission findById(Long roelId);
}
