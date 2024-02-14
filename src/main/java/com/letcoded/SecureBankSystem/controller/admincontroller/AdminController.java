package com.letcoded.SecureBankSystem.controller.admincontroller;


import com.letcoded.SecureBankSystem.Service.user.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Configuration("api/v1/admin")
public class AdminController {
    private final UserService userService;

    public  AdminController(UserService userService){
        this.userService = userService;
    }
 public ResponseEntity<List<String>> getAllUsersWithStrongPassword(){
        List<String> allUsersWithStrongPassword = userService.getAllUsersWithStrongPassword();
        return  ResponseEntity.ok(allUsersWithStrongPassword);
 }
}
