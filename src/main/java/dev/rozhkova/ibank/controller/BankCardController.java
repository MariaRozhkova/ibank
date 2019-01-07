package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.BankCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/bankCard")
public class BankCardController {
    private final BankCardService bankCardService;

    @PostMapping("/create")
    public ResponseEntity createBankCard(@RequestBody final BankCardDto bankCardDto) {
        try {
            bankCardService.createBankCard(bankCardDto);
            return new ResponseEntity<>("Bank card created", HttpStatus.CREATED);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity getAllBankCard() {
        try {
            final List<BankCardDto> allBankCard = bankCardService.getAllBankCard();
            if (allBankCard != null) {
                return new ResponseEntity<>(allBankCard, FOUND);
            } else {
                return new ResponseEntity(NOT_FOUND);
            }
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity removeBankCard(@RequestBody final BankCardDto bankCardDto) {
        try {
            bankCardService.removeBankCard(bankCardDto);
            return new ResponseEntity<>("Bank Card removed", OK);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/lock/{id}")
    public ResponseEntity lockBankCard(@PathVariable("id") final Long id) {
        try {
            bankCardService.lockCardById(id);
            return new ResponseEntity<>("Bank card has been locked", OK);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/unlock/{id}")
    public ResponseEntity unlockBankCard(@PathVariable("id") final Long id) {
        try {
            bankCardService.unlockCardById(id);
            return new ResponseEntity<>("Bank card has been locked", OK);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), INTERNAL_SERVER_ERROR);
        }
    }
}