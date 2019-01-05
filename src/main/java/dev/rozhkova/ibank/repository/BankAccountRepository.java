package dev.rozhkova.ibank.repository;

import dev.rozhkova.ibank.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    //List<BankAccountEntity> findByUser(UserEntity user);
    List<BankAccountEntity> findByUserId(Long id);
    //BankAccountEntity findByUserAndId(UserEntity user, Long id);
    BankAccountEntity findByIdAndUserId(Long bankAccountId, Long userId);

    @Modifying
    @Transactional
    @Query("update BankAccountEntity b set b.moneyAmount =:moneyAmount where b.id =:id")
    void updateMoneyAmount(@Param("moneyAmount") Double moneyAmount, @Param("id") Long id);
}