package com.mycompany.recipeapi.controller;

import com.mycompany.recipeapi.dto.UserDTO;
import com.mycompany.recipeapi.entity.UserEntity;
import com.mycompany.recipeapi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    UserDTO userDTO = null;
    UserEntity userEntity = null;

    @Before
    public void init(){
        userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setEmail("testuser@gmail.com");
        userDTO.setPassword("Test@123");

        userEntity = new UserEntity();
        userEntity.setUserName(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
    }

    @Test
    public void test_register_user(){

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("bdszmvdfvdv-dhe$3");
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);

        ResponseEntity<?> responseEntity = authController.registerUser(userDTO);
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());

    }

    @Test
    public void test_register_when_userName_exist(){

        Optional<UserEntity> userEntityOpt = Optional.of(userEntity);
        Mockito.when(userRepository.findByUserName(userDTO.getUsername())).thenReturn(userEntityOpt);

        ResponseEntity<?> responseEntity = authController.registerUser(userDTO);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void test_register_when_email_exist(){

        Optional<UserEntity> userEntityOpt = Optional.of(userEntity);
        Mockito.when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(userEntityOpt);

        ResponseEntity<?> responseEntity = authController.registerUser(userDTO);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }
}
