package com.gpsolutions.ibank.repository;

import com.gpsolutions.ibank.entity.BankCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends JpaRepository<BankCardEntity, Long> {
}