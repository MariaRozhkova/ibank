package com.gpsolutions.ibank.converter;

import com.gpsolutions.ibank.dto.BankCardDto;
import com.gpsolutions.ibank.dto.BankDto;
import com.gpsolutions.ibank.entity.BankCardEntity;
import com.gpsolutions.ibank.entity.BankEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankCardConverter implements DtoDboConverter<BankCardDto, BankCardEntity> {
    @Override
    public BankCardDto convertToDto(BankCardEntity dbo) {
        final BankCardDto bankCardDto = new BankCardDto();
        BeanUtils.copyProperties(dbo, bankCardDto);
        return bankCardDto;
    }

    @Override
    public BankCardEntity convertToDbo(BankCardDto dto) {
        final BankCardEntity bankCardEntity = new BankCardEntity();
        BeanUtils.copyProperties(dto, bankCardEntity);
        return bankCardEntity;
    }

    public List<BankCardDto> convertToDto(final List<BankCardEntity> dbo) {
        if (dbo != null) {
            final List<BankCardDto> list = new ArrayList<>();
            for (final BankCardEntity bankCardEntity : dbo) {
                final BankCardDto convertToDto = convertToDto(bankCardEntity);
                list.add(convertToDto);
            }
            return list;
        } else {
            return null;
        }
    }
}