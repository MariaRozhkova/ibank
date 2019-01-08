package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankAccountConverter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountConverter bankAccountConverter;

    public List<BankAccountDto> getAllBankAccount() throws UserException {
        return bankAccountRepository.findAll().stream().map(bankAccountConverter::convertToDto).collect(Collectors.toList());
    }

    public void createBankAccount(final BankAccountDto bankAccountDto) throws UserException {
        final BankAccountEntity bankAccountEntity = bankAccountConverter.convertToDbo(bankAccountDto);
        bankAccountRepository.save(bankAccountEntity);
    }

    public void removeBankAccount(final Long id) throws UserException {
        bankAccountRepository.deleteById(id);
    }

    public List<BankAccountDto> getAllBankAccountByUserId(final Long id) throws UserException {
        return bankAccountRepository.findByUserId(id).stream().map(bankAccountConverter::convertToDto).collect(Collectors.toList());
    }

    public BankAccountDto getBankAccountByIdAndUserId(final Long bankAccountId, final Long userId) throws UserException {
        return bankAccountConverter.convertToDto(bankAccountRepository.findByIdAndUserId(bankAccountId, userId));
    }

    public void updateMoneyAmount(final Double moneyAmount, final Long id) {
        bankAccountRepository.updateMoneyAmount(moneyAmount, id);
    }


    public void lockAllBankAccountEntityByUser(final UserEntity user) {
        final List<BankAccountEntity> accountEntityList = bankAccountRepository.findByUser(user);
        if (accountEntityList.size() != 0) {
            for (final BankAccountEntity entity : accountEntityList) {
                if (entity.getEnabled()) {
                    entity.setEnabled(false);
                    for (final BankCardEntity card: entity.getBankCardEntity()){
                        card.setEnabled(false);
                    }
                }
            }
            bankAccountRepository.saveAll(accountEntityList);
        }
    }

    public void unlockAllBankAccountEntityByUser(final UserEntity user) {
        final List<BankAccountEntity> accountEntityList = bankAccountRepository.findByUser(user);
        if (accountEntityList.size() != 0) {
            for (final BankAccountEntity entity : accountEntityList) {
                if (!entity.getEnabled()) {
                    entity.setEnabled(true);
                    for (final BankCardEntity card: entity.getBankCardEntity()){
                        card.setEnabled(true);
                    }
                }
            }
            bankAccountRepository.saveAll(accountEntityList);
        }
    }

    public void lockBankAccountByUserAndId(final UserEntity user, final Long bankAccountId) {
        final BankAccountEntity entity = bankAccountRepository.findByUserAndId(user, bankAccountId);
        if (entity.getEnabled()) {
            entity.setEnabled(false);
            for (final BankCardEntity card: entity.getBankCardEntity()){
                card.setEnabled(false);
            }
            bankAccountRepository.save(entity);
        }
    }

    public void unlockBankAccountByAccountNumber(final String accountNumber) {
        final BankAccountEntity entity = bankAccountRepository.findByAccountNumber(accountNumber);
        unlockCards(entity);

    }

    private void unlockCards(final BankAccountEntity entity) {
        if (!entity.getEnabled()) {
            entity.setEnabled(true);
            for (final BankCardEntity card: entity.getBankCardEntity()){
                card.setEnabled(true);
            }
            bankAccountRepository.save(entity);
        }
    }

    public void unlockBankAccountByUserAndId(final UserEntity user, final Long bankAccountId) {
        final BankAccountEntity entity = bankAccountRepository.findByUserAndId(user, bankAccountId);
        unlockCards(entity);

    }

    public BankAccountDto getBankAccountByUserAndId(final UserEntity user, final Long bankAccountId) {
        return bankAccountConverter.convertToDto(bankAccountRepository.findByUserAndId(user, bankAccountId));
    }

}