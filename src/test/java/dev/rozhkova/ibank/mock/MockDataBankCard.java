package dev.rozhkova.ibank.mock;

import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;

public class MockDataBankCard {

    public static BankCardEntity bankCardEntity() {
        final BankCardEntity bankCardEntity = new BankCardEntity();
        bankCardEntity.setCardNumber("384567123956");
        bankCardEntity.setCardHolderName("INSTANT CARD");
        bankCardEntity.setDate("09/21");
        bankCardEntity.setCvv(453);
        bankCardEntity.setEnabled(true);
        final BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankCardEntity.setBankAccount(bankAccountEntity);
        return bankCardEntity;
    }

    public static BankCardDto bankCardDto() {
        final BankCardDto bankCardDto = new BankCardDto();
        bankCardDto.setCardNumber("384567123956");
        bankCardDto.setCardHolderName("INSTANT CARD");
        bankCardDto.setDate("09/21");
        bankCardDto.setCvv(453);
        bankCardDto.setEnabled(true);
        return bankCardDto;
    }
}