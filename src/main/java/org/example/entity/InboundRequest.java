package org.example.entity;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundRequest {
    @Embedded
    private LesseName lesseName;
    private String companyName;
    private String pickUpLocation;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;
    private String summary;
}