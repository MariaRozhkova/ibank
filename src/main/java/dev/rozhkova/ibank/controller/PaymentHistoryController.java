package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.converter.PaymentHistoryConverter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.BankAccountService;
import dev.rozhkova.ibank.service.BankCardService;
import dev.rozhkova.ibank.service.PaymentHistoryService;
import dev.rozhkova.ibank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;
    private final BankAccountService bankAccountService;
    private final BankCardService bankCardService;
    private final UserService userService;

    @GetMapping("/users/{id}/paymentHistory/list")
    public ResponseEntity getAllPaymentHistoryByUserId(@PathVariable final Long id) {
        try{
            final List<PaymentHistoryDto> paymentHistoryDtos = paymentHistoryService.getAllPaymentHistoryByUserId(id);
            if (paymentHistoryDtos != null) {
                return new ResponseEntity<>(paymentHistoryDtos, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}/paymentHistory")
    public ResponseEntity getAllPaymentHistoryByBankCardNumber(@RequestParam final String bankCardNumber) {
        try{
            final List<PaymentHistoryDto> paymentHistoryDtos = paymentHistoryService.getAllPaymentHistoryByBankCardNumber(bankCardNumber);
            if (paymentHistoryDtos != null) {
                return new ResponseEntity<>(paymentHistoryDtos, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/{userId}/payments/create")
    public ResponseEntity makePayment(@PathVariable final Long userId,
                                      @RequestBody final PaymentHistoryDto paymentHistoryDto) {
        try {
            final BankAccountEntity bankAccountEntity = bankCardService.getBankAccountByCardNumber(paymentHistoryDto.getBankCard().getCardNumber());
            Double moneyOnAccount = bankAccountEntity.getMoneyAmount();
            Double moneyAfterOperation = moneyOnAccount - paymentHistoryDto.getMoneyAmount();

            bankAccountService.updateMoneyAmount(moneyAfterOperation, bankAccountEntity.getId());
            UserEntity userEntity = userService.getUserEntityById(userId);
            paymentHistoryService.makePayment(userEntity, paymentHistoryDto);
            return new ResponseEntity<>("Payment record created", HttpStatus.CREATED);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}