package com.letcoded.SecureBankSystem.Service.auth;

import com.letcoded.SecureBankSystem.bo.auth.AuthenticationResponse;
import com.letcoded.SecureBankSystem.bo.auth.CreateLoginRequest;
import com.letcoded.SecureBankSystem.bo.auth.LogoutResponse;
import com.letcode.SecureBankSystem.bo.auth.CreateSignupRequest;

public interface AuthService {

    void signup(CreateSignupRequest createSignupRequest);

    AuthenticationResponse login(CreateLoginRequest createLoginRequest);

    void logout(LogoutResponse logoutResponse);
}
