package com.hariprasath.springsecuritywithjwt.service.services;

import com.hariprasath.springsecuritywithjwt.entity.SecurityRequest;
import com.hariprasath.springsecuritywithjwt.entity.SecurityResponse;
import com.hariprasath.springsecuritywithjwt.repository.SecurityRepository;
import com.hariprasath.springsecuritywithjwt.service.SecutiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SecutiryServiceImpl implements SecutiryService {
    @Autowired
    SecurityRepository securityRepository;

     private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void createUser(SecurityRequest securityRequest) {
        SecurityResponse securityResponse = new SecurityResponse();
        securityResponse.setUsername(securityRequest.getUsername());
        securityResponse.setPassword(encoder.encode(securityRequest.getPassword()));
        securityResponse.setRole(securityRequest.getRole());
        securityRepository.save(securityResponse);
    }

    @Override
    public List<SecurityResponse> getUserDetails() {
        List<SecurityResponse> userDetails = securityRepository.findAll();
        log.info(userDetails.toString());
        return userDetails;

    }
}
