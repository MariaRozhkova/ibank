package dev.rozhkova.ibank.repository;

import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistoryEntity, Long> {
    List<PaymentHistoryEntity> findByBankCardCardNumber(String number);
    List<PaymentHistoryEntity> findByUserId(Long id);

}