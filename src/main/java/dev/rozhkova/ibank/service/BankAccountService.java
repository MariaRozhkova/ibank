package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankAccountConverter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
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
}