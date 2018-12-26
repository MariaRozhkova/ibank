package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankCardConverter;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.exceptions.UserException;
import dev.rozhkova.ibank.repository.BankCardRepository;
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