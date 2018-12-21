package com.gpsolutions.ibank.repository;

import com.gpsolutions.ibank.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDbo, Long> {
    UserDbo findByLogin(String login);
}