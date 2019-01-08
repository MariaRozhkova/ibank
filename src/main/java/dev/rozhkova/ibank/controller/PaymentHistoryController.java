package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;
    private final BankAccountService bankAccountService;
    private final BankCardService bankCardService;
    private final UserService userService;
    private final SavedPaymentService savedPaymentService;

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
            final Double moneyOnAccount = bankAccountEntity.getMoneyAmount();
            final Double moneyAfterOperation = moneyOnAccount - paymentHistoryDto.getMoneyAmount();

            bankAccountService.updateMoneyAmount(moneyAfterOperation, bankAccountEntity.getId());
            final UserEntity userEntity = userService.getUserEntityById(userId);
            paymentHistoryService.makePayment(userEntity, paymentHistoryDto);
            return new ResponseEntity<>("Payment record created", HttpStatus.CREATED);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = {"/users/{userId}/saved/payments/{savedPaymentId}/create", "/users/{userId}/saved/payments/{savedPaymentId}/create/{moneyAmount}"})
    public ResponseEntity makePayment(@PathVariable final Optional<Double> moneyAmount,
                                      @PathVariable final Long userId,
                                      @PathVariable final Long savedPaymentId) {
        try {
            final SavedPaymentDto savedPaymentDto = savedPaymentService.getSavedPaymentById(savedPaymentId);
            final BankAccountEntity bankAccountEntity = bankCardService.getBankAccountByCardNumber(savedPaymentDto.getBankCard().getCardNumber());
            final Double moneyOnAccount = bankAccountEntity.getMoneyAmount();
            final Double moneyAfterOperation;

            PaymentHistoryDto paymentHistoryDto = new PaymentHistoryDto();
            double money = moneyAmount.get();
            if (money == 0) {
                moneyAfterOperation = moneyOnAccount - savedPaymentDto.getMoneyAmount();
                paymentHistoryDto.setMoneyAmount(savedPaymentDto.getMoneyAmount());
            } else {
                moneyAfterOperation = moneyOnAccount - money;
                paymentHistoryDto.setMoneyAmount(money);
            }

            paymentHistoryDto.setBankCard(savedPaymentDto.getBankCard());
            paymentHistoryDto.setPaymentAccount(savedPaymentDto.getPaymentAccount());
            paymentHistoryDto.setPaymentOperation(savedPaymentDto.getPaymentOperation());

            bankAccountService.updateMoneyAmount(moneyAfterOperation, bankAccountEntity.getId());
            final UserEntity userEntity = userService.getUserEntityById(userId);
            paymentHistoryService.makePayment(userEntity, paymentHistoryDto);
            return new ResponseEntity<>("Payment record created", HttpStatus.CREATED);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}