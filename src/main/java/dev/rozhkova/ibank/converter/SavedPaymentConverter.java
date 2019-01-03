package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.entity.SavedPaymentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SavedPaymentConverter implements DtoDboConverter<SavedPaymentDto, SavedPaymentEntity>{
    @Override
    public SavedPaymentDto convertToDto(SavedPaymentEntity dbo) {
        final SavedPaymentDto savedPaymentDto = new SavedPaymentDto();
        BeanUtils.copyProperties(dbo, savedPaymentDto);
        return savedPaymentDto;
    }

    @Override
    public SavedPaymentEntity convertToDbo(SavedPaymentDto dto) {
        final SavedPaymentEntity savedPaymentEntity = new SavedPaymentEntity();
        BeanUtils.copyProperties(dto, savedPaymentEntity);
        return savedPaymentEntity;
    }
}
