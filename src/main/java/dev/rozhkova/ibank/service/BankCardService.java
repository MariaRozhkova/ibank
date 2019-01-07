package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankCardConverter;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.exception.UserException;
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

    public void removeBankCardById(final Long id) throws UserException {
        bankCardRepository.deleteById(id);
    }

    public List<BankCardDto> getAllBankCardByBankAccountId(final Long id) throws UserException{
        final List<BankCardEntity> bankCardEntities = bankCardRepository.findByBankAccountId(id);
        return bankCardConverter.convertToDto(bankCardEntities);
    }

    public BankAccountEntity getBankAccountByCardNumber(final String cardNumber) {
        return bankCardRepository.findByCardNumber(cardNumber).getBankAccount();
    }
}