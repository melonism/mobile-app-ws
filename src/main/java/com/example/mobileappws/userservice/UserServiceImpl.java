package com.example.mobileappws.userservice;

import com.example.mobileappws.domain.UserDetailsRequestModel;
import com.example.mobileappws.domain.UserRest;
import com.example.mobileappws.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    Map<String, UserRest> users;
    Utils utils;


    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = this. utils.generateUserId();
        returnValue.setUserId(userId);

        if(users ==  null) {
            users = new HashMap<>();
        }
        users.put(userId, returnValue);

        return returnValue;
    }
}
