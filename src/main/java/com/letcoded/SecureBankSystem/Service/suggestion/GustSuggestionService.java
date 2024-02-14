package com.letcoded.SecureBankSystem.Service.suggestion;

import com.letcoded.SecureBankSystem.Repository.GustSuggestionRepository;
import com.letcoded.SecureBankSystem.bo.CreateSuggestionRequest;
import com.letcoded.SecureBankSystem.processor.SuggestionProcessor;

public interface GustSuggestionService {

    void createSuggestion (  CreateSuggestionRequest createSuggestionRequest);



}
