package com.secure.employ.service;

import com.secure.employ.dto.LoginDto;
import com.secure.employ.dto.UserDto;
import com.secure.employ.entity.User;
import com.secure.employ.exception.InvalidCredentialsException;
import com.secure.employ.exception.UsernameAlreadyExistsException;
import com.secure.employ.repository.UserRepository;
import com.secure.employ.util.ApiResponse;
import com.secure.employ.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ApiResponse registerUser(UserDto userDto) throws RuntimeException {
        try {
            // Check if the username is already taken
            if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
                throw new UsernameAlreadyExistsException("Username is already taken");
            }

            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(encodedPassword);
            user.setRole(userDto.getRole());
            userRepository.save(user);
            ApiResponse response = new ApiResponse("User registered successfully", HttpStatus.OK, null);
            return response;
        }catch(RuntimeException e){
            throw new RuntimeException("Error while adding User");
        }
    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtToken.generateToken(loginDto.getUsername());
                ApiResponse response = new ApiResponse("Login Successful", HttpStatus.OK, token);
                return response;
            } else {
                return new ApiResponse("Login Failed", HttpStatus.UNAUTHORIZED, null);

            }
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
    }
}
