package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.PaymentOperationDto;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import dev.rozhkova.ibank.entity.PaymentOperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentHistoryConverter implements DtoDboConverter<PaymentHistoryDto, PaymentHistoryEntity> {

    private final BankCardConverter bankCardConverter;
    private final PaymentOperationConverter paymentOperationConverter;

    @Autowired
    public PaymentHistoryConverter(final BankCardConverter bankCardConverter, final PaymentOperationConverter paymentOperationConverter) {
        this.bankCardConverter = bankCardConverter;
        this.paymentOperationConverter = paymentOperationConverter;
    }

    @Override
    public PaymentHistoryDto convertToDto(final PaymentHistoryEntity dbo) {
        final PaymentHistoryDto paymentHistoryDto = new PaymentHistoryDto();
        final BankCardDto bankCardDto = bankCardConverter.convertToDto(dbo.getBankCard());
        paymentHistoryDto.setId(dbo.getId());
        paymentHistoryDto.setBankCard(bankCardDto);
        paymentHistoryDto.setDateOperation(dbo.getDateOperation());
        paymentHistoryDto.setMoneyAmount(dbo.getMoneyAmount());
        final PaymentOperationDto paymentOperationDto = paymentOperationConverter.convertToDto(dbo.getPaymentOperation());
        paymentHistoryDto.setPaymentOperation(paymentOperationDto);
        paymentHistoryDto.setPaymentAccount(dbo.getPaymentAccount());
        return paymentHistoryDto;
    }

    @Override
    public PaymentHistoryEntity convertToDbo(final PaymentHistoryDto dto) {
        final PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
        final BankCardEntity bankCardEntity = bankCardConverter.convertToDbo(dto.getBankCard());
        paymentHistoryEntity.setBankCard(bankCardEntity);
        paymentHistoryEntity.setMoneyAmount(dto.getMoneyAmount());
        final PaymentOperationEntity paymentOperationEntity = paymentOperationConverter.convertToDbo(dto.getPaymentOperation());
        paymentHistoryEntity.setPaymentOperation(paymentOperationEntity);
        paymentHistoryEntity.setPaymentAccount(dto.getPaymentAccount());
        return paymentHistoryEntity;
    }
}