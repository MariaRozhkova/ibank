package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankAccountConverter;
import dev.rozhkova.ibank.converter.BankCardConverter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.mock.MockDataBankAccount;
import dev.rozhkova.ibank.mock.MockDataUser;
import dev.rozhkova.ibank.repository.BankAccountRepository;
import dev.rozhkova.ibank.repository.BankCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceTest {

    @InjectMocks
    private BankAccountService bankAccountService;

    @Mock
    private BankAccountRepository bankAccountRepository;

    private BankCardConverter bankCardConverter = new BankCardConverter();

    @Spy
    private BankAccountConverter bankAccountConverter = new BankAccountConverter(bankCardConverter);


    @Test
    public void getAllBankAccount() throws UserException {
        final List<BankAccountEntity> findAllResult = new ArrayList<>();
        final BankAccountEntity bankAccountEntity = MockDataBankAccount.bankAccountEntity();
        findAllResult.add(bankAccountEntity);
        findAllResult.add(bankAccountEntity);
        doReturn(findAllResult).when(bankAccountRepository).findAll();

        final List<BankAccountDto> bankAccountDtos = bankAccountService.getAllBankAccount();
        verify(bankAccountRepository, times(1)).findAll();

        assertEquals(findAllResult.size(), bankAccountDtos.size());
        for (final BankAccountDto bankAccountDto : bankAccountDtos) {
            assertEquals(bankAccountEntity.getMoneyAmount(), bankAccountDto.getMoneyAmount());
            assertEquals(bankAccountEntity.getAccountNumber(), bankAccountDto.getAccountNumber());
            assertEquals(bankAccountEntity.getEnabled(), bankAccountDto.getEnabled());
        }
    }

    @Test
    public void createBankAccount() throws UserException {
        final BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountNumber("478134978234");
        bankAccountEntity.setMoneyAmount(320d);
        bankAccountEntity.setEnabled(true);
        doReturn(bankAccountEntity).when(bankAccountRepository).save(bankAccountEntity);

        final BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setAccountNumber("478134978234");
        bankAccountDto.setMoneyAmount(320d);
        bankAccountDto.setEnabled(true);
        bankAccountService.createBankAccount(bankAccountDto);

        verify(bankAccountRepository, times(1)).save(bankAccountEntity);
    }

    @Test
    public void removeBankAccount() throws UserException {
        doNothing().when(bankAccountRepository).deleteById(1L);
        bankAccountService.removeBankAccount(1L);
        verify(bankAccountRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllBankAccountByUserId() {
    }

    @Test
    public void getBankAccountByIdAndUserId() {
    }

    @Test
    public void updateMoneyAmount() {
    }
}