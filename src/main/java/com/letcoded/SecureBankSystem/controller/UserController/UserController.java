package com.letcoded.SecureBankSystem.controller.UserController;

import com.letcoded.SecureBankSystem.Service.UserService;
import com.letcoded.SecureBankSystem.bo.User.CreateUserRequest;
import com.letcoded.SecureBankSystem.bo.User.UpdateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            userService.saveUser(createUserRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User Created successfully");
    }

    @PutMapping("/update-user-status")
    public ResponseEntity<String> updateUser(@RequestParam Long userId, @RequestBody UpdateUserRequest updateUserRequest){
        try{
            userService.updateUserStatus((updateUserRequest));
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User Updated successfully");
    }
}

