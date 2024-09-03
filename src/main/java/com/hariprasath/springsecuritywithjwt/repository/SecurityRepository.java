package com.hariprasath.springsecuritywithjwt.repository;

import com.hariprasath.springsecuritywithjwt.entity.SecurityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityResponse, Long> {
    List<SecurityResponse> findByUsername(String username);
}
