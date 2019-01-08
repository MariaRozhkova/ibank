package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankCardConverter;
import dev.rozhkova.ibank.converter.DtoDboConverter;
import dev.rozhkova.ibank.converter.PaymentOperationConverter;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.entity.SavedPaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavedPaymentConverter implements DtoDboConverter<SavedPaymentDto, SavedPaymentEntity> {
    private final BankCardConverter bankCardConverter;
    private final PaymentOperationConverter paymentOperationConverter;

    @Autowired
    public SavedPaymentConverter(final BankCardConverter bankCardConverter, final PaymentOperationConverter paymentOperationConverter) {
        this.bankCardConverter = bankCardConverter;
        this.paymentOperationConverter = paymentOperationConverter;
    }

    @Override
    public SavedPaymentDto convertToDto(SavedPaymentEntity dbo) {
        final SavedPaymentDto savedPaymentDto = new SavedPaymentDto();

        savedPaymentDto.setId(dbo.getId());
        savedPaymentDto.setBankCard(bankCardConverter.convertToDto(dbo.getBankCard()));
        savedPaymentDto.setMoneyAmount(dbo.getMoneyAmount());
        savedPaymentDto.setPaymentAccount(dbo.getPaymentAccount());
        savedPaymentDto.setPaymentOperation(paymentOperationConverter.convertToDto(dbo.getPaymentOperation()));

        return savedPaymentDto;
    }

    @Override
    public SavedPaymentEntity convertToDbo(SavedPaymentDto dto) {
        final SavedPaymentEntity savedPaymentEntity = new SavedPaymentEntity();

        savedPaymentEntity.setId(dto.getId());
        savedPaymentEntity.setBankCard(bankCardConverter.convertToDbo(dto.getBankCard()));
        savedPaymentEntity.setMoneyAmount(dto.getMoneyAmount());
        savedPaymentEntity.setPaymentAccount(dto.getPaymentAccount());
        savedPaymentEntity.setPaymentOperation(paymentOperationConverter.convertToDbo(dto.getPaymentOperation()));

        return savedPaymentEntity;
    }
}