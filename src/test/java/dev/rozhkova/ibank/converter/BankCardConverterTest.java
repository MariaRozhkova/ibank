package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.mock.MockDataBankCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankCardConverterTest {

    private final BankCardConverter bankCardConverter = new BankCardConverter();

    @Test
    public void convertToDto() {
        final BankCardEntity bankCardEntity = MockDataBankCard.bankCardEntity();
        final BankCardDto bankCardDto = bankCardConverter.convertToDto(bankCardEntity);
        assertEquals(bankCardEntity.getCardNumber(), bankCardDto.getCardNumber());
        assertEquals(bankCardEntity.getCardHolderName(), bankCardDto.getCardHolderName());
        assertEquals(bankCardEntity.getEnabled(), bankCardDto.getEnabled());
        assertEquals(bankCardEntity.getDate(), bankCardDto.getDate());
        assertEquals(bankCardEntity.getCvv(), bankCardDto.getCvv());
    }

    @Test
    public void convertToDbo() {
        final BankCardDto bankCardDto = MockDataBankCard.bankCardDto();
        final BankCardEntity bankCardEntity = bankCardConverter.convertToDbo(bankCardDto);
        assertEquals(bankCardEntity.getCardNumber(), bankCardDto.getCardNumber());
        assertEquals(bankCardEntity.getCardHolderName(), bankCardDto.getCardHolderName());
        assertEquals(bankCardEntity.getEnabled(), bankCardDto.getEnabled());
        assertEquals(bankCardEntity.getDate(), bankCardDto.getDate());
        assertEquals(bankCardEntity.getCvv(), bankCardDto.getCvv());
    }
}