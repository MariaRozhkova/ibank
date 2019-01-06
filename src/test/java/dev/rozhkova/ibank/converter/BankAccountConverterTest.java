package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.mock.MockDataBankAccount;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountConverterTest {

    private final BankCardConverter bankCardConverter = new BankCardConverter();

    private final  BankAccountConverter bankAccountConverter = new BankAccountConverter(bankCardConverter);

    @Test
    public void convertToDto() {
        final BankAccountEntity bankAccountEntity = MockDataBankAccount.bankAccountEntity();
        final BankAccountDto bankAccountDto = bankAccountConverter.convertToDto(bankAccountEntity);
        assertEquals(bankAccountEntity.getAccountNumber(), bankAccountDto.getAccountNumber());
        assertEquals(bankAccountEntity.getMoneyAmount(), bankAccountDto.getMoneyAmount());
        assertEquals(bankAccountEntity.getEnabled(), bankAccountDto.getEnabled());
    }

    @Test
    public void convertToDbo() {
        final BankAccountDto bankAccountDto = MockDataBankAccount.bankAccountDto();
        final BankAccountEntity bankAccountEntity = bankAccountConverter.convertToDbo(bankAccountDto);
        assertEquals(bankAccountDto.getAccountNumber(), bankAccountEntity.getAccountNumber());
        assertEquals(bankAccountDto.getMoneyAmount(), bankAccountEntity.getMoneyAmount());
        assertEquals(bankAccountDto.getEnabled(), bankAccountEntity.getEnabled());
    }
}