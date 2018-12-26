package com.gpsolutions.ibank.service;

import com.gpsolutions.ibank.converter.BankCardConverter;
import com.gpsolutions.ibank.dto.BankCardDto;
import com.gpsolutions.ibank.entity.BankCardEntity;
import com.gpsolutions.ibank.exceptions.UserException;
import com.gpsolutions.ibank.repository.BankCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BankCardService {
    private final BankCardRepository bankCardRepository;
    private final BankCardConverter bankCardConverter;

    public void createBankCard(final BankCardDto bankCardDto) throws UserException {
        bankCardRepository.save(bankCardConverter.convertToDbo(bankCardDto));
    }

    public List<BankCardDto> getAllBankCard() throws UserException {
        final List<BankCardEntity> allBankCard = bankCardRepository.findAll();
        return bankCardConverter.convertToDto(new ArrayList<>(allBankCard));
    }

    public void removeBankCard(final BankCardDto bankCardDto) throws UserException {
        bankCardRepository.delete(bankCardConverter.convertToDbo(bankCardDto));
    }
}