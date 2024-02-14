package com.letcoded.SecureBankSystem.controller.suggestionController;

import com.letcoded.SecureBankSystem.Service.suggestion.GustSuggestionService;
import com.letcoded.SecureBankSystem.bo.CreateSuggestionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/suggestion")
public class SuggestionController {
    private final GustSuggestionService suggestionService;

    public SuggestionController(GustSuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }


    @PostMapping("/api/suggestions")
    public ResponseEntity<String> recieveAndProcessSuggestions(@RequestBody CreateSuggestionRequest suggestionText){
        suggestionService.createSuggestion(suggestionText);
        return ResponseEntity.ok("A success message");
    }
}
