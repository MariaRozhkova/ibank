package dev.rozhkova.ibank.mock;

import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;

import java.util.ArrayList;
import java.util.List;

public class MockDataBankAccount {
    public static BankAccountEntity bankAccountEntity() {
        final BankAccountEntity bankAccountEntity = new BankAccountEntity();
        final BankCardEntity bankCardEntity = MockDataBankCard.bankCardEntity();
        final List<BankCardEntity> bankCardEntities = new ArrayList<>();
        bankCardEntities.add(bankCardEntity);
        bankAccountEntity.setBankCardEntity(bankCardEntities);
        bankAccountEntity.setId(1L);
        bankAccountEntity.setAccountNumber("478134978234");
        bankAccountEntity.setMoneyAmount(320d);
        bankAccountEntity.setEnabled(true);
        return bankAccountEntity;
    }

    public static BankAccountDto bankAccountDto() {
        final BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setAccountNumber("478134978234");
        bankAccountDto.setMoneyAmount(320d);
        bankAccountDto.setEnabled(true);
        final List<BankCardDto> bankCardDtos = new ArrayList<>();
        bankAccountDto.setBankCardDtos(bankCardDtos);
        return bankAccountDto;
    }
}