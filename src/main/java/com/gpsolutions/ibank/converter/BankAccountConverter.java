package com.gpsolutions.ibank.converter;

import com.gpsolutions.ibank.dbo.BankAccountDbo;
import com.gpsolutions.ibank.dbo.RoleDbo;
import com.gpsolutions.ibank.dto.BankAccountDto;
import com.gpsolutions.ibank.dto.RoleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountConverter implements DtoDboConverter<BankAccountDto, BankAccountDbo> {
    @Override
    public BankAccountDto convertToDto(BankAccountDbo dbo) {
        final BankAccountDto bankAccountDto = new BankAccountDto();
        BeanUtils.copyProperties(dbo, bankAccountDto);
        return bankAccountDto;
    }

    @Override
    public BankAccountDbo convertToDbo(BankAccountDto dto) {
        final BankAccountDbo bankAccountDbo = new BankAccountDbo();
        BeanUtils.copyProperties(dto, bankAccountDbo);
        return bankAccountDbo;
    }
}