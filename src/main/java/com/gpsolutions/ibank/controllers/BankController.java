package com.gpsolutions.ibank.controllers;

import com.gpsolutions.ibank.dto.BankDto;
import com.gpsolutions.ibank.exceptions.UserException;
import com.gpsolutions.ibank.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    @GetMapping("/info")
    public ResponseEntity getBankInfo() {
        try {
            BankDto bankDto = bankService.getBankInfo();
            return new ResponseEntity(bankDto, HttpStatus.FOUND);
        } catch (UserException ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}