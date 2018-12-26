package com.gpsolutions.ibank.converter;

import com.gpsolutions.ibank.entity.BankAccountEntity;
import com.gpsolutions.ibank.dto.BankAccountDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountConverter implements DtoDboConverter<BankAccountDto, BankAccountEntity> {
    @Override
    public BankAccountDto convertToDto(BankAccountEntity dbo) {
        final BankAccountDto bankAccountDto = new BankAccountDto();
        BeanUtils.copyProperties(dbo, bankAccountDto);
        return bankAccountDto;
    }

    @Override
    public BankAccountEntity convertToDbo(BankAccountDto dto) {
        final BankAccountEntity bankAccountEntity = new BankAccountEntity();
        BeanUtils.copyProperties(dto, bankAccountEntity);
        return bankAccountEntity;
    }
}