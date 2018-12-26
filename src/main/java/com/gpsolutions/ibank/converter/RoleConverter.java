package com.gpsolutions.ibank.converter;

import com.gpsolutions.ibank.entity.RoleEntity;
import com.gpsolutions.ibank.dto.RoleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoleConverter implements DtoDboConverter<RoleDto, RoleEntity> {
    @Override
    public RoleDto convertToDto(RoleEntity dbo) {
        final RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(dbo, roleDto);
        return roleDto;
    }

    @Override
    public RoleEntity convertToDbo(RoleDto dto) {
        final RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(dto, roleEntity);
        return roleEntity;
    }
}