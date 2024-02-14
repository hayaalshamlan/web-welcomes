package com.letcoded.SecureBankSystem.Service.suggestion;

import com.letcoded.SecureBankSystem.Repository.GustSuggestionRepository;
import com.letcoded.SecureBankSystem.Util.enums.Status;
import com.letcoded.SecureBankSystem.bo.CreateSuggestionRequest;
import com.letcoded.SecureBankSystem.entity.GustSuggestionEntity;
import com.letcoded.SecureBankSystem.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GustSuggestionServiceImpl implements GustSuggestionService {

    private final GustSuggestionRepository suggestionRepository;

    public GustSuggestionServiceImpl(GustSuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }


    @Override
    public void createSuggestion(CreateSuggestionRequest createSuggestionRequest) {
        GustSuggestionEntity gustSuggestionEntity = new GustSuggestionEntity();
        gustSuggestionEntity.setRate(createSuggestionRequest.getRate());
        gustSuggestionEntity.setTitle(createSuggestionRequest.getRate());
        suggestionRepository.save(gustSuggestionEntity);
    }

}


