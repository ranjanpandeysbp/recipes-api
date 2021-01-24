package com.mycompany.recipeapi.controller;

import com.mycompany.recipeapi.dto.MessageDTO;
import com.mycompany.recipeapi.dto.UserDTO;
import com.mycompany.recipeapi.entity.UserEntity;
import com.mycompany.recipeapi.repository.UserRepository;
import com.mycompany.recipeapi.security.jwt.JwtUtils;
import com.mycompany.recipeapi.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/health")
    public String health(){
        return "healthy";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO userDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new UserDTO(jwt,
                userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {

        if (userRepository.findByUserName(userDTO.getUsername()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDTO("Error: Username is already taken!"));
        }
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDTO("Error: Email is already taken!"));
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setName(userDTO.getName());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageDTO("User registered successfully!"));


    }

}

