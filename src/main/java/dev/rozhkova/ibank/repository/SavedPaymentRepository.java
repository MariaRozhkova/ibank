package dev.rozhkova.ibank.repository;

import dev.rozhkova.ibank.entity.SavedPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPaymentRepository extends JpaRepository<SavedPaymentEntity, Long> {
    List<SavedPaymentEntity> findByUserId(Long id);
}
