package com.letcoded.SecureBankSystem.controller;

import com.letcoded.SecureBankSystem.bo.CreateFarewellRequest;
import org.springframework.web.bind.annotation.*;


@RestController
public class CodedController {

    @GetMapping("/sayHi")
    public String sayHi() {
        return "Welcome to coded";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @PostMapping("/Farewell")
    public String Farewell(@RequestBody CreateFarewellRequest createFarewellRequest) {
        return "GoodBye, " + createFarewellRequest.getName();
    }
}
