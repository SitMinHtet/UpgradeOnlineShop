package com.smh.myprojectone.service;

import com.smh.myprojectone.model.CustomUserDetail;
import com.smh.myprojectone.model.User;
import com.smh.myprojectone.repository.UserRepository;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class CustomUserDetailService{
        /*implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() ->
            new UsernameNotFoundException("User is not Found"));

        return user.map(CustomUserDetail::new).get();
    }

         */
}
