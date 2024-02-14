package com.letcoded.SecureBankSystem.Service.user;

import com.letcoded.SecureBankSystem.bo.User.CreateUserRequest;
import com.letcoded.SecureBankSystem.bo.User.UpdateUserRequest;
import com.letcoded.SecureBankSystem.bo.User.UpdateUserStatusRequest;
import com.letcoded.SecureBankSystem.entity.UserEntity;

import java.util.List;

public interface UserService {

    void saveUser(CreateUserRequest createUserRequest);


    void updateUserStatus(Long userID,UpdateUserStatusRequest updateUserStatusRequest);

    List<String> getAllUsersWithStrongPassword ();
}
