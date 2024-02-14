package com.letcoded.SecureBankSystem.Repository;

import com.letcoded.SecureBankSystem.entity.GustSuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GustSuggestionRepository extends JpaRepository<GustSuggestionEntity,Long> {


}
