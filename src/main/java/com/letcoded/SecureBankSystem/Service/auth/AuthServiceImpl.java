package com.letcoded.SecureBankSystem.Service.auth;


import com.letcoded.SecureBankSystem.Repository.RoleRepository;
import com.letcoded.SecureBankSystem.Repository.UserRepository;
import com.letcoded.SecureBankSystem.Util.enums.Roles;
import com.letcoded.SecureBankSystem.Util.enums.Status;
import com.letcoded.SecureBankSystem.Util.exception.UserNotFoundException;
import com.letcoded.SecureBankSystem.bo.auth.AuthenticationResponse;
import com.letcoded.SecureBankSystem.bo.auth.CreateLoginRequest;
import com.letcoded.SecureBankSystem.bo.auth.LogoutResponse;
import com.letcoded.SecureBankSystem.bo.customeUserDetails.CustomeUserDetails;
import com.letcoded.SecureBankSystem.config.JWTUtil;
import com.letcoded.SecureBankSystem.entity.RoleEntity;
import com.letcoded.SecureBankSystem.entity.UserEntity;
import com.letcoded.SecureBankSystem.Util.exception.BodyGuardException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.letcode.SecureBankSystem.bo.auth.CreateSignupRequest;

@Service
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final JWTUtil jwtUtil;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JWTUtil jwtUtil, RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void signup(CreateSignupRequest createSignupRequest) {
        RoleEntity roleEntity= roleRepository.findRoleEntityByTitle(Roles.user.name())
                .orElseThrow(() -> new BodyGuardException("no Roles Found"));
        UserEntity user= new UserEntity();
        user.setName(createSignupRequest.getName());
        user.setUsername(createSignupRequest.getUsername());
        user.setPhoneNumber(createSignupRequest.getPhoneNumber());
        user.setEmail(createSignupRequest.getEmail());
        user.setRoles(roleEntity);
        user.setStatus(Status.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(CreateLoginRequest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(),"username");
        requiredNonNull(createLoginRequest.getPassword(),"password");

        String username= createLoginRequest.getUsername().toLowerCase();
        String password= createLoginRequest.getPassword();

        authentication(username, password);

        CustomeUserDetails userDetails= userDetailsService.loadUserByUsername(username);

        String accessToken = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer "+accessToken);
        return response;
    }

    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"token");
    }

    private void requiredNonNull(Object obj, String name){
        if(obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name + " can't be empty");
        }
    }

    private void authentication(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BodyGuardException e){
            throw new BodyGuardException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw  new UserNotFoundException("Incorrect username");
        }
    }
}