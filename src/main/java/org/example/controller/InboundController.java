package org.example.controller;

import org.example.entity.InboundRequest;
import org.example.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inbound/v1/")
public class InboundController {
    @Autowired
    InboundService inboundService;

    @PostMapping("application")
    public String jobOrderApplication(@RequestBody InboundRequest inboundRequest) {
        try {
            int number = inboundService.jobOrderApplication(inboundRequest);
            return "Application Created Successfully -> Application Number : " + number;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
