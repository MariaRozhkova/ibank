package com.gpsolutions.ibank.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BankAccountOperationController {

    @GetMapping(value = "/users/{id}/bankAccounts")
    public ResponseEntity getBankAccountListByUser(@PathVariable Long id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/users/{userId}/bankAccounts/{bankAccountId}")
    public ResponseEntity changeStatusOfBankAccount(@PathVariable Long userId, @PathVariable Long bankAccountId,
                                                    @RequestBody Boolean enabled) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/users/{userId}/bankAccounts/{bankAccountId}/bankCards/{bankCardId}")
    public ResponseEntity changeStatusOfBankCard(@PathVariable Long userId, @PathVariable Long bankAccountId,
                                                 @PathVariable Long bankCardId, @RequestBody Boolean enabled) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}/bankAccounts/{bankAccountId}/bankCards/{bankCardId}")
    public ResponseEntity getAllBankCardOfBankAccount(@PathVariable Long userId, @PathVariable Long bankAccountId,
                                                      @PathVariable Long bankCardId) {
        return new ResponseEntity(HttpStatus.OK);
    }

}