package com.letcoded.SecureBankSystem.Service;

import com.letcoded.SecureBankSystem.Repository.UserRepository;
import com.letcoded.SecureBankSystem.Util.enums.Status;
import com.letcoded.SecureBankSystem.bo.User.CreateUserRequest;
import com.letcoded.SecureBankSystem.bo.User.UpdateUserRequest;
import com.letcoded.SecureBankSystem.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPhoneNumber(createUserRequest.getPhone());
        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public void updateUserStatus(UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = userRepository.findById(updateUserRequest.getUserId())
                .orElseThrow();
        if (!updateUserRequest.getStatus().equals("ACTIVE")
                || !updateUserRequest.getStatus().equals("INACTIVE")) {
            throw new IllegalArgumentException("The status should be ACTIVE or INACTIVE");
        }
        userEntity.setStatus(Status.valueOf(updateUserRequest.getStatus()));
        userRepository.save(userEntity);
    }
}
