package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="application_details")
@Entity
public class InboundResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long applicationNumber;
    @Embedded
    private LesseName lesseName;
    private String companyName;
    private String pickUpLocation;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;
    private String summary;
}