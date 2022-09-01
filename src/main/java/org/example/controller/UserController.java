package org.example.controller;


import com.spring.services.api.UserApi;
import com.spring.services.model.AuthUser;
import com.spring.services.model.Token;
import com.spring.services.model.User;
import io.swagger.annotations.Api;
import org.example.entity.AuthRequest;
import org.example.entity.UserEntity;
import org.example.exception.ErrorHandler;
import org.example.serviceImpl.UserServiceImpl;
import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
@Api(value = "User", tags = "User")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController implements UserApi {

    Base64.Encoder encoder = Base64.getEncoder();
    Base64.Decoder decoder = Base64.getDecoder();
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public ResponseEntity<Void> userAddUserPost(User user){
        return userService.postUser(user);
    }


    @Override
    public ResponseEntity<Void> userDeleteUserByIdDelete(Integer userId){
        try {
            return userService.deleteUser(userId);
        }
        catch(Exception e){
            throw new ErrorHandler();
        }
    }


    @Override
    public ResponseEntity<List<User>> userGetAllUsersGet(){
        return userService.getAllUser();
    }


    @Override
    public ResponseEntity<User> userGetUserByIdGet(Integer userId){
        try {
            return userService.getElementById(userId);
        }catch (Exception e){
            throw new ErrorHandler();
        }
    }

    @Override
    public ResponseEntity<Void> userUpdateUserPost(User user){
        return userService.updateUser(user);
    }

    @GetMapping("/getUser/{email}")
    public UserEntity getUser(@PathVariable String email){
        try {
            return userService.findUserByEmail(email);
        }catch (Exception e){
            throw new ErrorHandler();
        }
    }

    @Override
    @CrossOrigin(origins = "*")
    public ResponseEntity<Token> userGetTokenPost(AuthUser authUser)  {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUser.getUserName(),authUser.getPassword()));
        } catch (Exception e) {
            throw new ErrorHandler();
        }
        Token token=new Token();
        token.setToken(jwtUtil.generateToken(authUser.getUserName()));
        return new ResponseEntity(token, HttpStatus.OK);
    }
//    @PostMapping("/getToken")
//    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
//        } catch (Exception e) {
//            throw new Exception("Invalid username password");
//        }
//        return jwtUtil.generateToken(authRequest.getUserName());
//    }
}
