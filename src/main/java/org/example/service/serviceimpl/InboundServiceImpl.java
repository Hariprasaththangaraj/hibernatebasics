package org.example.service.serviceimpl;

import org.example.config.HibernateConfig;
import org.example.entity.InboundRequest;
import org.example.entity.InboundResponse;
import org.example.entity.LesseName;
import org.example.service.InboundService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class InboundServiceImpl implements InboundService {
    @Override
    public int jobOrderApplication(InboundRequest inboundRequest) {
        Session session = HibernateConfig.getSessionFactory().openSession();

        Random random = new Random();
        int number = 100000 + random.nextInt(900000);

        LocalDateTime pickUpDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS);

        InboundResponse inboundResponse = new InboundResponse();

        LesseName lesseName = new LesseName();
        lesseName.setFirstName(inboundRequest.getLesseName().getFirstName());
        lesseName.setLastName(inboundRequest.getLesseName().getLastName());
        //inboundResponse.setId((long) number);
        inboundResponse.setApplicationNumber((long) number);
        inboundResponse.setLesseName(lesseName);
        inboundResponse.setCompanyName(inboundRequest.getCompanyName());
        inboundResponse.setPickUpLocation(inboundRequest.getPickUpLocation());
        inboundResponse.setPickUpDate(pickUpDate);
        inboundResponse.setDropOffDate(pickUpDate.plusDays(1));
        inboundResponse.setSummary(inboundRequest.getSummary());

        session.beginTransaction();
        session.save(inboundResponse);
        session.getTransaction().commit();
        session.close();

        return number;
    }


}
