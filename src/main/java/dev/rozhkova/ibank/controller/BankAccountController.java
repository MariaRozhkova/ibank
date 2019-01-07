package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.config.EmailConfiguration;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.RequestDto;
import dev.rozhkova.ibank.entity.UnlockingDataEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.BankAccountService;
import dev.rozhkova.ibank.service.UnlockingDataEntityService;
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
@RequestMapping("/bankAccount")
@AllArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final EmailConfiguration emailConfiguration;
    private final UnlockingDataEntityService unlockingDataEntityService;

    @GetMapping("/list")
    public ResponseEntity getAllBankAccount() {
        try {
            final List<BankAccountDto> allBankAccount = bankAccountService.getAllBankAccount();
            if (allBankAccount != null) {
                return new ResponseEntity<>(allBankAccount, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity createBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        try {
            bankAccountService.createBankAccount(bankAccountDto);
            return new ResponseEntity<>("New bank account created", HttpStatus.CREATED);

        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeBankAccount(@PathVariable final Long id) {
        try{
            bankAccountService.removeBankAccount(id);
            return new ResponseEntity<>("Bank account removed", OK);
        } catch (final UserException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * this method sends email with come code to unlock bank account
     *
     * @param requestDto - entity that includes email and account number
     * @return ResponseEntity
     */
    @PostMapping("/bank_account/sendUnlockLetter")
    public ResponseEntity unlockBankAccount(@RequestBody final RequestDto requestDto) {
        final String generatedValue = UUID.randomUUID().toString().replaceAll("-", "");
        final String recipient = requestDto.getEmail();
        final String msg = "This is your confirmation link to unlock bank account: <a href=\"http://localhost:8080/api/confirmation?account="
                + requestDto.getAccountNumber() + "&key=" + generatedValue + "\">confirm account unlocking</a>";
        final String subject = "Confirmation from iBank";
        final UnlockingDataEntity newData = new UnlockingDataEntity();
        newData.setAccountNumber(requestDto.getAccountNumber());
        newData.setGeneratedValue(generatedValue);
        newData.setValid(true);
        unlockingDataEntityService.save(newData);
        final String host = emailConfiguration.getHost();
        final String port = emailConfiguration.getPort();
        final String fromEmail = emailConfiguration.getUser();
        final String pass = emailConfiguration.getPass();
        try {
            EmailUtility.sendEmailWithAttachment(host, port, fromEmail, pass, recipient, subject, msg, null);
            return new ResponseEntity<>("New unlocking request created", HttpStatus.CREATED);
        } catch (final MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/bank_account/remove")
    public ResponseEntity removeBankAccount(@RequestBody final BankAccountDto bankAccountDto) throws UserException {

        bankAccountService.removeBankAccount(bankAccountDto.getId());
        return new ResponseEntity<>("Bank account removed", OK);
    }
}