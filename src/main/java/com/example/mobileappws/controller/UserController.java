package com.example.mobileappws.controller;

import com.example.mobileappws.domain.UserDetailsRequestModel;
import com.example.mobileappws.domain.UserRest;
import com.example.mobileappws.domain.UserUpdateDetailsRequestModel;
import com.example.mobileappws.exceptions.UserServiceException;
import com.example.mobileappws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    Map<String,UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser(@RequestParam(value="page", defaultValue = "1") int page,
                          @RequestParam(value="limit", defaultValue = "25") int limit,
                          @RequestParam(value="sort", defaultValue = "desc", required = false) String sort) {
        return "get user was called with page = " + page + " limit " + limit + " and sort " + sort;
    }

    @GetMapping(path="/{userId}",
            produces = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

        if(true) throw new UserServiceException("A user service exception message is thrown");

        if(users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = userService.createUser(userDetails);

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PutMapping( path="/{userId}",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId,
        @Valid @RequestBody UserUpdateDetailsRequestModel userDetails) {
        if(users.containsKey(userId)) {
            UserRest userRest = users.get(userId);
            userRest.setFirstName(userDetails.getFirstName());
            userRest.setLastName(userDetails.getLastName());

            users.put(userId, userRest);

            return new ResponseEntity<>(userRest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping( path="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
