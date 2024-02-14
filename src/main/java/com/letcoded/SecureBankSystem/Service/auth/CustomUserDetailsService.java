package com.letcoded.SecureBankSystem.Service.auth;

import com.letcoded.SecureBankSystem.Repository.UserRepository;
import com.letcoded.SecureBankSystem.bo.customeUserDetails.CustomeUserDetails;
import com.letcoded.SecureBankSystem.entity.UserEntity;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomeUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return buildCustomUserDetailsOfUsername(s);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private CustomeUserDetails buildCustomUserDetailsOfUsername(String username) throws NotFoundException {
        UserEntity user=userRepository.findByUsername(username)
                .orElseThrow();
        if (user == null){
            throw new NotFoundException("User not found");
        }
        CustomeUserDetails userDetails = new CustomeUserDetails();
        userDetails.setId(user.getId());
        userDetails.setUserName(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setRole(user.getRoles().getTitle().name());

        return userDetails;
    }
}