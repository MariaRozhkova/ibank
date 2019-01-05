package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.PaymentHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

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
}