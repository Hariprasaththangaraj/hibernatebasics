package com.hariprasath.springsecuritywithjwt.controller;

import com.hariprasath.springsecuritywithjwt.entity.SecurityRequest;
import com.hariprasath.springsecuritywithjwt.entity.SecurityResponse;
import com.hariprasath.springsecuritywithjwt.entity.Users;
import com.hariprasath.springsecuritywithjwt.service.SecutiryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SecurityController {

    @Autowired
    SecutiryService securityService;

    //Uncomment and implement methods as needed
    @PostMapping("/v1/register")
    public ResponseEntity<String> createUser(@RequestBody SecurityRequest securityRequest) {
        try {
            securityService.createUser(securityRequest);
            return ResponseEntity.ok("User created");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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

    @PostMapping("/v1/login")
    public String userLogin(@RequestBody Users users) {
        return securityService.verifyUserNameAndPassword(users);
    }
}
