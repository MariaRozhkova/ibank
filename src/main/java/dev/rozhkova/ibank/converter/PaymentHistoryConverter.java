package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import org.springframework.beans.BeanUtils;

public class PaymentHistoryConverter implements DtoDboConverter<PaymentHistoryDto, PaymentHistoryEntity> {
    @Override
    public PaymentHistoryDto convertToDto(PaymentHistoryEntity dbo) {
        final PaymentHistoryDto paymentHistoryDto = new PaymentHistoryDto();
        BeanUtils.copyProperties(dbo, paymentHistoryDto);
        return paymentHistoryDto;
    }

    @Override
    public PaymentHistoryEntity convertToDbo(PaymentHistoryDto dto) {
        final PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
        BeanUtils.copyProperties(dto, paymentHistoryEntity);
        return paymentHistoryEntity;
    }
}