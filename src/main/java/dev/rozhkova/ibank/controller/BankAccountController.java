package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.configuration.EmailConfiguration;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.RequestDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.BankAccountService;
import dev.rozhkova.ibank.utils.EmailUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
/*@RequestMapping("/bank_account")*/
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final EmailConfiguration emailConfiguration;

    @GetMapping("/bank_account/list")
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

    @PostMapping("/bank_account/create")
    public ResponseEntity createBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        try {
            bankAccountService.createBankAccount(bankAccountDto);
            return new ResponseEntity<>("New bank account created", HttpStatus.CREATED);

        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bank_account/unlock/{number}")
    public ResponseEntity unlockBankAccount(@RequestBody final RequestDto requestDto, @PathVariable("number") String number) {
        System.out.println("Number: " + number);
        String generatedValue = UUID.randomUUID().toString().replaceAll("-", "");
        String recipient = requestDto.getEmail();
        String msg = "This is your confirmation code: <a href=\"http://localhost:8080/myapp/registration?account="
                + requestDto.getAccountNumber() + "&key=" + generatedValue + "\">activate</a>";
        String subject = "Confirmation from iBank";
        String host = emailConfiguration.getHost();
        String port = emailConfiguration.getPort();
        String fromEmail = emailConfiguration.getUser();
        String pass = emailConfiguration.getPass();
        try {
            EmailUtility.sendEmailWithAttachment(host, port, fromEmail, pass, recipient, subject, msg, null);
            return new ResponseEntity<>("New unlocking request created", HttpStatus.CREATED);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/bank_account/remove")
    public ResponseEntity removeBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        try {
            bankAccountService.removeBankAccount(bankAccountDto);
            return new ResponseEntity<>("Bank account removed", OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}