package com.gpsolutions.ibank.converter;

import com.gpsolutions.ibank.dbo.RoleDbo;
import com.gpsolutions.ibank.dto.RoleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoleConverter implements DtoDboConverter<RoleDto, RoleDbo> {
    @Override
    public RoleDto convertToDto(RoleDbo dbo) {
        final RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(dbo, roleDto);
        return roleDto;
    }

    @Override
    public RoleDbo convertToDbo(RoleDto dto) {
        final RoleDbo roleDbo = new RoleDbo();
        BeanUtils.copyProperties(dto, roleDbo);
        return roleDbo;
    }
}