package com.gpsolutions.ibank.service;

import com.gpsolutions.ibank.converter.BankConverter;
import com.gpsolutions.ibank.dto.BankDto;
import com.gpsolutions.ibank.exceptions.UserException;
import com.gpsolutions.ibank.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankService {

    private BankRepository bankRepository;
    private BankConverter bankConverter;

    public BankDto getBankInfo() throws UserException {
        return bankConverter.convertToDto(bankRepository.findById(1L).get());
    }
}