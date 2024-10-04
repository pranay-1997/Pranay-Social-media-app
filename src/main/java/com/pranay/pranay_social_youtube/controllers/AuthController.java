package com.pranay.pranay_social_youtube.controllers;

import com.pranay.pranay_social_youtube.config.JwtProvider;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.UserRepository;
import com.pranay.pranay_social_youtube.request.LoginRequest;
import com.pranay.pranay_social_youtube.response.AuthResponse;
import com.pranay.pranay_social_youtube.services.UserService;
import com.pranay.pranay_social_youtube.services.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());
        if(isExist!=null){
            throw new Exception("email already used with another account");
        }
        User newUser=new User();


        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setLastName(user.getLastName());
        // newUser.setId(user.getId());

        User savedUser=userRepository.save(newUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail()
                ,savedUser.getPassword());
        String token= JwtProvider.generateToken(authentication);
        AuthResponse response=new AuthResponse(token,"Register success");
        return response;

    }
  @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication=
                authenticate(loginRequest.getEmail(),loginRequest.getPassword());

        String token= JwtProvider.generateToken(authentication);
        AuthResponse response=new AuthResponse(token,"Login success");
        return response;

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails=customerUserDetailsService.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("password not matched");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,
                userDetails.getAuthorities());
    }
}
