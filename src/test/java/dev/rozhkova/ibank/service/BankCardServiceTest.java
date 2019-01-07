package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankCardConverter;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.mock.MockDataBankCard;
import dev.rozhkova.ibank.repository.BankCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BankCardServiceTest {

    @InjectMocks
    private BankCardService bankCardService;

    @Mock
    private BankCardRepository bankCardRepository;

    @Spy
    private BankCardConverter bankCardConverter;

    @Test
    public void createBankCard() throws UserException {
        final BankCardEntity bankCardEntity = new BankCardEntity();
        bankCardEntity.setCardNumber("384567123956");
        bankCardEntity.setCardHolderName("INSTANT CARD");
        bankCardEntity.setDate("09/21");
        bankCardEntity.setCvv(453);
        bankCardEntity.setEnabled(true);
        final BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setId(1L);
        bankCardEntity.setBankAccount(bankAccountEntity);

        doReturn(bankCardEntity).when(bankCardRepository).save(any(BankCardEntity.class));

        bankCardService.createBankCard(new BankCardDto());

        verify(bankCardRepository, times(1)).save(any(BankCardEntity.class));
    }

    @Test
    public void getAllBankCard() throws UserException {
        final List<BankCardEntity> findAllResult = new ArrayList<>();
        final BankCardEntity bankCardEntity = MockDataBankCard.bankCardEntity();
        findAllResult.add(bankCardEntity);
        findAllResult.add(bankCardEntity);
        doReturn(findAllResult).when(bankCardRepository).findAll();

        final List<BankCardDto> bankCardDtos = bankCardService.getAllBankCard();
        verify(bankCardRepository, times(1)).findAll();

        assertEquals(findAllResult.size(), bankCardDtos.size());
        for (final BankCardDto bankCardDto : bankCardDtos) {
            assertEquals(bankCardEntity.getCardNumber(), bankCardDto.getCardNumber());
            assertEquals(bankCardEntity.getCardHolderName(), bankCardDto.getCardHolderName());
            assertEquals(bankCardEntity.getDate(), bankCardDto.getDate());
            assertEquals(bankCardEntity.getCvv(), bankCardDto.getCvv());
            assertEquals(bankCardEntity.getEnabled(), bankCardDto.getEnabled());
        }
    }

    @Test
    public void removeBankCard() throws UserException {
        doNothing().when(bankCardRepository).deleteById(1L);
        bankCardService.removeBankCardById(1L);
        verify(bankCardRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllBankCardByBankAccountId() {
    }

    @Test
    public void getBankAccountByCardNumber() {
        final BankCardEntity bankCardEntity = MockDataBankCard.bankCardEntity();
        doReturn(bankCardEntity).when(bankCardRepository).findByCardNumber("384567123956");
        final BankAccountEntity bankAccountEntity = bankCardService.getBankAccountByCardNumber("384567123956");
        verify(bankCardRepository, times(1)).findByCardNumber("384567123956");
    }
}