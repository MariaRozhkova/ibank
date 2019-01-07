package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.converter.BankAccountConverter;
import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.BankAccountService;
import dev.rozhkova.ibank.service.BankCardService;
import dev.rozhkova.ibank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountOperationController {
    private final BankAccountService bankAccountService;
    private final BankCardService bankCardService;
    private final UserService userService;

    @GetMapping("/users/{id}/bankAccount/list")
    public ResponseEntity getAllBankAccountByUserId(@PathVariable final Long id) {
        try{
            final List<BankAccountDto> allBankAccountByUser = bankAccountService.getAllBankAccountByUserId(id);
            if (allBankAccountByUser != null) {
                return new ResponseEntity<>(allBankAccountByUser, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}/bankAccount/{bankAccountId}")
    public ResponseEntity getBankAccountByIdAndUserId(@PathVariable final Long bankAccountId,
                                                      @PathVariable final Long userId) {
        try{
            final BankAccountDto bankAccountByIdAndUser = bankAccountService.getBankAccountByIdAndUserId(bankAccountId, userId);
            return new ResponseEntity<>(bankAccountByIdAndUser, HttpStatus.FOUND);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}/bankAccount/{bankAccountId}/bankCard/list")
    public ResponseEntity getAllBankCardByBankAccountId(@PathVariable final Long userId,
                                                        @PathVariable final Long bankAccountId) {
        try {
            final List<BankCardDto> allBankCardByBankAccount = bankCardService.getAllBankCardByBankAccountId(bankAccountId);
            if (allBankCardByBankAccount != null) {
                return new ResponseEntity<>(allBankCardByBankAccount, HttpStatus.FOUND);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * this method lock bank account of user if exists
     *
     * @param id        - user id
     * @param accountId - bank account id
      */
    @GetMapping("/users/{id}/bankAccount/{accountId}/lock")
    public ResponseEntity<String> lockBankAccountByUserAndId(@PathVariable("id") final Long id, @PathVariable("accountId") final Long accountId) {
        try {
            final UserEntity user = userService.getUserEntityById(id);
            bankAccountService.lockBankAccountByUserAndId(user, accountId);
            return new ResponseEntity<>("Account has been locked successfully!", HttpStatus.FOUND);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * this method unlock bank account of user if exists
     *
     * @param id        - user id
     * @param accountId - bank account id
     */
    @GetMapping("/users/{id}/bankAccount/{accountId}/unlock")
    public ResponseEntity<String> unlockBankAccountByUserAndId(@PathVariable("id") final Long id, @PathVariable("accountId") final Long accountId) {
        try {
            final UserEntity user = userService.getUserEntityById(id);
            bankAccountService.unlockBankAccountByUserAndId(user, accountId);
            return new ResponseEntity<>("Account has been unlocked successfully!", HttpStatus.FOUND);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}