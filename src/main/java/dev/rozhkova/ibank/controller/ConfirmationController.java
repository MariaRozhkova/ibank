package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.entity.UnlockingDataEntity;
import dev.rozhkova.ibank.service.BankAccountService;
import dev.rozhkova.ibank.service.UnlockingDataEntityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class ConfirmationController {
    private final UnlockingDataEntityService unlockingDataEntityService;
    private final BankAccountService bankAccountService;

    @GetMapping(value = "confirmation")
    public ResponseEntity unlockBankAccount(@RequestParam("account") final String account, @RequestParam("key") final String key) {
        final UnlockingDataEntity findingEntity = unlockingDataEntityService.findByAccountNumberAndGeneratedValue(account, key);
        if (findingEntity != null) {
            bankAccountService.unlockBankAccountByAccountNumber(account);
            findingEntity.setValid(false);
            unlockingDataEntityService.save(findingEntity);
            return new ResponseEntity<>("Account has been unlocked!", HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
