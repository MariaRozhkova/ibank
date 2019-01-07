package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.entity.UnlockingDataEntity;
import dev.rozhkova.ibank.repository.UnlockingDataEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnlockingDataEntityService {

    private final UnlockingDataEntityRepository unlockingDataEntityRepository;


    @Autowired
    public UnlockingDataEntityService(final UnlockingDataEntityRepository unlockingDataEntityRepository) {
        this.unlockingDataEntityRepository = unlockingDataEntityRepository;
    }

    public void save(final UnlockingDataEntity entity) {
        unlockingDataEntityRepository.save(entity);
    }

    public UnlockingDataEntity findByAccountNumberAndGeneratedValue(final String accountNumber, final String generatedValue) {
        return unlockingDataEntityRepository.findByAccountNumberAndGeneratedValueAndValid(accountNumber, generatedValue, true);
    }
}
