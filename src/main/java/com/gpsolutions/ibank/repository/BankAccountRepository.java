package com.gpsolutions.ibank.repository;

import com.gpsolutions.ibank.dbo.BankAccountDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccountDbo, Long> {
}