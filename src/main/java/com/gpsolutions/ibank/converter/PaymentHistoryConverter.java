package com.gpsolutions.ibank.converter;

import com.gpsolutions.ibank.dto.BankDto;
import com.gpsolutions.ibank.dto.PaymentHistoryDto;
import com.gpsolutions.ibank.entity.BankEntity;
import com.gpsolutions.ibank.entity.PaymentHistoryEntity;
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