package com.hariprasath.springsecuritywithjwt.service.services;

import com.hariprasath.springsecuritywithjwt.config.UserPrincipals;
import com.hariprasath.springsecuritywithjwt.entity.Users;
import com.hariprasath.springsecuritywithjwt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityConfigService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if (users == null) {
            try {
                throw new Exception("User Not Found !!!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new UserPrincipals(users);
    }
}
