package com.gpsolutions.ibank.service;

import com.gpsolutions.ibank.converter.BankAccountConverter;
import com.gpsolutions.ibank.dto.BankAccountDto;
import com.gpsolutions.ibank.entity.BankAccountEntity;
import com.gpsolutions.ibank.exceptions.UserException;
import com.gpsolutions.ibank.repository.BankAccountRepository;
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
        BankAccountEntity bankAccountEntity = bankAccountConverter.convertToDbo(bankAccountDto);
        bankAccountRepository.save(bankAccountEntity);
    }

    public void removeBankAccount(final BankAccountDto bankAccountDto) throws UserException {
        bankAccountRepository.delete(bankAccountConverter.convertToDbo(bankAccountDto));
    }
}