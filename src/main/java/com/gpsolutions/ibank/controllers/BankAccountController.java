package com.gpsolutions.ibank.controllers;

import com.gpsolutions.ibank.dto.BankAccountDto;
import com.gpsolutions.ibank.exceptions.UserException;
import com.gpsolutions.ibank.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/bank_account")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @GetMapping("/list")
    public ResponseEntity getAllBankAccount() {
        try {
            final List<BankAccountDto> allBankAccount = bankAccountService.getAllBankAccount();
            if (allBankAccount != null) {
                return new ResponseEntity<>(allBankAccount, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity createBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        try {
            bankAccountService.createBankAccount(bankAccountDto);
            return new ResponseEntity<>("New bank account created", HttpStatus.CREATED);

        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        try{
            bankAccountService.removeBankAccount(bankAccountDto);
            return new ResponseEntity<>("Bank account removed", OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}