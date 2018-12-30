package dev.rozhkova.ibank.repository;

import dev.rozhkova.ibank.entity.UnlockingDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnlockingDataEntityRepository extends JpaRepository<UnlockingDataEntity, Long> {
    UnlockingDataEntity findFirstByAccountNumberOrderByAccountNumberDesc(final String accountNumber);
}
