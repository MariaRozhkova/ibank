package com.gpsolutions.ibank.controllers;

import com.gpsolutions.ibank.dto.RoleDto;
import com.gpsolutions.ibank.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public List<RoleDto> getAllRoles() {
        return roleService.getRolesList();
    }
}