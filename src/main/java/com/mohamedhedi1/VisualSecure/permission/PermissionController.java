package com.mohamedhedi1.VisualSecure.permission;



import com.mohamedhedi1.VisualSecure.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final IPermissionService permissionService;

    @GetMapping("/all")
    public ResponseEntity<List<Permission>> getAllPermissions(){
        return new ResponseEntity<>(permissionService.getAllPermissions(), FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){
        return new ResponseEntity<>(permissionService.createPermission(permission), CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void createPermission(@PathVariable("id") Long permissionId){
        permissionService.deletePermission(permissionId);
    }
    @PostMapping("/remove-all-users-from-permission/{id}")
    public Permission removeAllUsersFromPermission(@PathVariable("id") Long permissionId){
        return permissionService.removeAllUserFromPermission(permissionId);
    }
    @PostMapping("/remove-user-from-permission")
    public User removeUserFromPermission(@RequestParam("userId")Long userId,
                                   @RequestParam("permissionId") Long permissionId){
        return permissionService.removeUserFromPermission(userId, permissionId);
    }

    @PostMapping("/assign-user-to-permission")
    public User assignUserToPermission(@RequestParam("userId")Long userId,
                                 @RequestParam("permissionId") Long permissionId){
        return permissionService.assignUerToPermission(userId, permissionId);
    }

}
