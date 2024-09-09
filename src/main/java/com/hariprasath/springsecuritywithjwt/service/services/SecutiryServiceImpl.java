package com.hariprasath.springsecuritywithjwt.service.services;

import com.hariprasath.springsecuritywithjwt.entity.SecurityRequest;
import com.hariprasath.springsecuritywithjwt.entity.SecurityResponse;
import com.hariprasath.springsecuritywithjwt.entity.Users;
import com.hariprasath.springsecuritywithjwt.repository.SecurityRepository;
import com.hariprasath.springsecuritywithjwt.service.SecutiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SecutiryServiceImpl implements SecutiryService {
    @Autowired
    SecurityRepository securityRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void createUser(SecurityRequest securityRequest) {

        // Check if username and password are not null or empty
        if (securityRequest.getUsername() == null || securityRequest.getUsername().isEmpty()) {
            throw new RuntimeException("Username must not be null or empty");
        }
        if (securityRequest.getPassword() == null || securityRequest.getPassword().isEmpty()) {
            throw new RuntimeException("Password must not be null or empty");
        }
        // Check for existing user
        List<SecurityResponse> existingUser = securityRepository.findByUsername(securityRequest.getUsername());
        if (existingUser.size() == 0) {
            SecurityResponse securityResponse = new SecurityResponse();
            securityResponse.setUsername(securityRequest.getUsername());
            securityResponse.setPassword(encoder.encode(securityRequest.getPassword()));
            securityResponse.setRole(securityRequest.getRole());
            securityRepository.save(securityResponse);
        } else {
            throw new RuntimeException("User with this username already exists");
        }
    }

    @Override
    public List<SecurityResponse> getUserDetails() {
        List<SecurityResponse> userDetails = securityRepository.findAll();
        log.info(userDetails.toString());
        return userDetails;
    }

    @Override
    public String verifyUserNameAndPassword(Users users) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(users.getUsername());
        }
        return "fail";
    }
}
