package com.mycompany.recipeapi.service;

import com.mycompany.recipeapi.entity.UserEntity;
import com.mycompany.recipeapi.exception.ResourceNotFoundException;
import com.mycompany.recipeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOpt = userRepository.findByUserName(username);
        if(!userEntityOpt.isPresent()){
            throw new ResourceNotFoundException("No user found with username " + username);
        }
        return UserDetailsImpl.build(userEntityOpt.get());
    }
}
