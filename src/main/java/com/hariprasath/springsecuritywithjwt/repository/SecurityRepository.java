package com.hariprasath.springsecuritywithjwt.repository;

import com.hariprasath.springsecuritywithjwt.entity.SecurityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityResponse, Long> {}
