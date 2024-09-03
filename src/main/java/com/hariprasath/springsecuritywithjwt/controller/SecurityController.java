package com.hariprasath.springsecuritywithjwt.controller;

import com.hariprasath.springsecuritywithjwt.entity.SecurityRequest;
import com.hariprasath.springsecuritywithjwt.entity.SecurityResponse;
import com.hariprasath.springsecuritywithjwt.service.SecutiryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SecurityController {

    @Autowired
    SecutiryService securityService;

    //     Uncomment and implement methods as needed
    @PostMapping("/v1/createUser")
    public String createUser(@RequestBody SecurityRequest securityRequest) {
        securityService.createUser(securityRequest);
        return "User created";
    }

//     @GetMapping("/v1/get-CSRF-token")
//     public CsrfToken test(HttpServletRequest token){
//         return (CsrfToken) token.getAttribute("_csrf");
//     }

    @GetMapping("/v1/get")
    public String test(HttpServletRequest request) {
        return "Its working  :" + request.getSession().getId();
    }

    @GetMapping("/v1/getUserDetails")
    public List<SecurityResponse> getUserDetails() {
        List<SecurityResponse> deails = securityService.getUserDetails();
        return deails;
    }
}
