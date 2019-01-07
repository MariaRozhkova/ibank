package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankCardConverter;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.BankCardRepository;
import dev.rozhkova.ibank.utils.DateUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        for (final BankCardEntity entity : allBankCard) {
            isCorrectCardDate(entity);
        }
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

    /*public List<BankCardDto> getAllBankCardByBankAccount(BankAccountEntity bankAccount) throws UserException {
        final List<BankCardEntity> allBankCardByAccount = bankCardRepository.findByBankAccount(bankAccount);
        for (BankCardEntity entity : allBankCardByAccount) {
            isCorrectCardDate(entity);
        }
        return bankCardConverter.convertToDto(new ArrayList<>(allBankCardByAccount));
    }*/

    public void lockCardById(final Long id) throws UserException {
        final BankCardEntity entity = bankCardRepository.getOne(id);
        if (entity != null) {
            if (entity.getEnabled()) {
                entity.setEnabled(false);
                bankCardRepository.save(entity);
            }
        } else throw new UserException("card not found!!!");
    }

    public void unlockCardById(final Long id) throws UserException {
        final BankCardEntity entity = bankCardRepository.getOne(id);
        if (entity != null) {
            if (isCorrectCardDate(entity)) {
                if (!entity.getEnabled()) {
                    entity.setEnabled(true);
                    bankCardRepository.save(entity);
                }
            }
        } else throw new UserException("card not found!!!");
    }

    public boolean isCorrectCardDate(final BankCardEntity entity) {
        final LocalDate cardDate = DateUtility.parse(entity.getDate());

        if (cardDate == null || cardDate.isBefore((LocalDate.now()))) {
            if (entity.getEnabled()) {
                entity.setEnabled(false);
                bankCardRepository.save(entity);
            }
            return false;
        }
        return true;
    }
}