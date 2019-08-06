package com.example.mobileappws.userservice;

import com.example.mobileappws.domain.UserDetailsRequestModel;
import com.example.mobileappws.domain.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
