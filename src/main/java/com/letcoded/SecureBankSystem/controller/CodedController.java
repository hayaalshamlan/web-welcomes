package com.letcoded.SecureBankSystem.controller;

import com.letcoded.SecureBankSystem.bo.Contact;
import com.letcoded.SecureBankSystem.bo.CreateFarewellRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CodedController {
    List<Contact> contasts = new ArrayList<>();

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

    @PostMapping("/AddContact")
    public String AddContact(@RequestBody Contact contact) {
        contasts.add(contact);
        return "AddContent " + "name: " + contact.getName() + " " + "email: " + " " + contact.getEmail() + " " + "phone: " + contact.getPhone();
    }

    @GetMapping("/GetContactDetails")
    public String GetContactDetails(@RequestBody Contact contact) {
        return "GetContactDetails" + " " + "name: " + contact.getName() + " " + "email: " + " " + contact.getEmail() + " " + "phone: " + contact.getPhone();
    }

}
