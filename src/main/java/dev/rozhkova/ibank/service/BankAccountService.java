package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankAccountConverter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountConverter bankAccountConverter;

    public List<BankAccountDto> getAllBankAccount() {
        return bankAccountRepository.findAll().stream().map(bankAccountConverter::convertToDto).collect(Collectors.toList());
    }

    public void createBankAccount(final BankAccountDto bankAccountDto) {
        BankAccountEntity bankAccountEntity = bankAccountConverter.convertToDbo(bankAccountDto);
        bankAccountRepository.save(bankAccountEntity);
    }

    public void removeBankAccount(final BankAccountDto bankAccountDto) {
        bankAccountRepository.delete(bankAccountConverter.convertToDbo(bankAccountDto));
    }

    public List<BankAccountDto> getAllBankAccountByUser(UserEntity user) {
        return bankAccountRepository.findByUser(user).stream().map(bankAccountConverter::convertToDto).collect(Collectors.toList());
    }

    public void lockAllBankAccountEntityByUser(UserEntity user) {
        List<BankAccountEntity> accountEntityList = bankAccountRepository.findByUser(user);
        if (accountEntityList.size() != 0) {
            for (BankAccountEntity entity : accountEntityList) {
                if (entity.getEnabled()) {
                    entity.setEnabled(false);
                    for (BankCardEntity card: entity.getBankCardList()){
                        card.setEnabled(false);
                    }
                }
            }
            bankAccountRepository.saveAll(accountEntityList);
        }
    }

    public void unlockAllBankAccountEntityByUser(UserEntity user) {
        List<BankAccountEntity> accountEntityList = bankAccountRepository.findByUser(user);
        if (accountEntityList.size() != 0) {
            for (BankAccountEntity entity : accountEntityList) {
                if (!entity.getEnabled()) {
                    entity.setEnabled(true);
                    for (BankCardEntity card: entity.getBankCardList()){
                        card.setEnabled(true);
                    }
                }
            }
            bankAccountRepository.saveAll(accountEntityList);
        }
    }

    public void lockBankAccountByUserAndId(UserEntity user, Long bankAccountId) {
        BankAccountEntity entity = bankAccountRepository.findByUserAndId(user, bankAccountId);
        if (entity.getEnabled()) {
            entity.setEnabled(false);
            for (BankCardEntity card: entity.getBankCardList()){
                card.setEnabled(false);
            }
            bankAccountRepository.save(entity);
        }
    }

    public void unlockBankAccountByAccountNumber(String accountNumber) {
        BankAccountEntity entity = bankAccountRepository.findByAccountNumber(accountNumber);
        unlockCards(entity);

    }

    private void unlockCards(BankAccountEntity entity) {
        if (!entity.getEnabled()) {
            entity.setEnabled(true);
            for (BankCardEntity card: entity.getBankCardList()){
                card.setEnabled(true);
            }
            bankAccountRepository.save(entity);
        }
    }

    public void unlockBankAccountByUserAndId(UserEntity user, Long bankAccountId) {
        BankAccountEntity entity = bankAccountRepository.findByUserAndId(user, bankAccountId);
        unlockCards(entity);

    }

    public BankAccountDto getBankAccountByUserAndId(UserEntity user, Long bankAccountId) {
        return bankAccountConverter.convertToDto(bankAccountRepository.findByUserAndId(user, bankAccountId));
    }


}