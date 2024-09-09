package com.hariprasath.springsecuritywithjwt.service;


import com.hariprasath.springsecuritywithjwt.entity.SecurityRequest;
import com.hariprasath.springsecuritywithjwt.entity.SecurityResponse;
import com.hariprasath.springsecuritywithjwt.entity.Users;

import java.util.List;

public interface SecutiryService {
    void createUser(SecurityRequest securityRequest);

    List<SecurityResponse> getUserDetails();

    String verifyUserNameAndPassword(Users users);
}
