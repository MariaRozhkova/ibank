package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.entity.UnlockingDataEntity;
import dev.rozhkova.ibank.repository.UnlockingDataEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnlockingDataEntityService {

    private final UnlockingDataEntityRepository unlockingDataEntityRepository;


    @Autowired
    public UnlockingDataEntityService(UnlockingDataEntityRepository unlockingDataEntityRepository) {
        this.unlockingDataEntityRepository = unlockingDataEntityRepository;
    }

    public void save(UnlockingDataEntity entity) {
        unlockingDataEntityRepository.save(entity);
    }

    public UnlockingDataEntity getLastByAccountNumber(String accountNumber) {
        return unlockingDataEntityRepository.findFirstByAccountNumberOrderByAccountNumberDesc(accountNumber);
    }
}
