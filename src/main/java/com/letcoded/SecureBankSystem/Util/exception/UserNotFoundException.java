package com.letcoded.SecureBankSystem.Util.exception;

public class UserNotFoundException extends RuntimeException{
    public  UserNotFoundException(String str){
        super(str);
    }
}
