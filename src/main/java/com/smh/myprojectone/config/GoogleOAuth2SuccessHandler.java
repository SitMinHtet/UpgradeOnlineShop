package com.smh.myprojectone.config;


import com.smh.myprojectone.model.Role;
import com.smh.myprojectone.model.User;
import com.smh.myprojectone.repository.RoleRepository;
import com.smh.myprojectone.repository.UserRepository;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class  GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email= token.getPrincipal().getAttributes().get("email").toString();

        if (userRepository.findUserByEmail(email).isPresent()){

        }
        else {
            User user = new User();
            user.setFirstName(token.getPrincipal().getAttributes().get("first-name").toString());
            user.setLastName(token.getPrincipal().getAttributes().get("last-name").toString());
            user.setEmail(email);
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findById(2).get());
            user.setRoles(roles);
            userRepository.save(user);
        }
        redirectStrategy.sendRedirect(request,response,"");
    }



    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

    }


}
