package dev.rozhkova.ibank.repository;

import dev.rozhkova.ibank.entity.BankCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardRepository extends JpaRepository<BankCardEntity, Long> {
    List<BankCardEntity> findByBankAccountId(Long id);
    BankCardEntity findByCardNumber(String cardNumber);
}