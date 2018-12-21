package com.gpsolutions.ibank.services;

import com.gpsolutions.ibank.converter.BankAccountConverter;
import com.gpsolutions.ibank.dto.BankAccountDto;
import com.gpsolutions.ibank.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountConverter bankAccountConverter;

    public BankAccountService(final BankAccountRepository bankAccountRepository,final BankAccountConverter bankAccountConverter) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountConverter = bankAccountConverter;
    }

    public List<BankAccountDto> getRolesList() {
        return bankAccountRepository.findAll().stream().map(bankAccountConverter::convertToDto).collect(Collectors.toList());
    }
}