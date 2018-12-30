package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.configuration.EmailConfiguration;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.RequestDto;
import dev.rozhkova.ibank.entity.UnlockingDataEntity;
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
@AllArgsConstructor
/*@RequestMapping("/bank_account")*/
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final EmailConfiguration emailConfiguration;
    private final UnlockingDataEntityService unlockingDataEntityService;

    @GetMapping("/bank_account/list")
    public ResponseEntity getAllBankAccount() {

        final List<BankAccountDto> allBankAccount = bankAccountService.getAllBankAccount();
        if (allBankAccount != null) {
            return new ResponseEntity<>(allBankAccount, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bank_account/create")
    public ResponseEntity createBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        bankAccountService.createBankAccount(bankAccountDto);
        return new ResponseEntity<>("New bank account created", HttpStatus.CREATED);


    }

    /**
     * this method sends email with come code to unlock bank account
     *
     * @param requestDto - entity that includes email and account number
     * @return ResponseEntity
     */
    @PostMapping("/bank_account/sendUnlockLetter")
    public ResponseEntity unlockBankAccount(@RequestBody final RequestDto requestDto) {
        String generatedValue = UUID.randomUUID().toString().replaceAll("-", "");
        String recipient = requestDto.getEmail();
        String msg = "This is your confirmation link to unlock bank account: <a href=\"http://localhost:8080/api/confirmation?account="
                + requestDto.getAccountNumber() + "&key=" + generatedValue + "\">confirm account unlocking</a>";
        String subject = "Confirmation from iBank";
        UnlockingDataEntity newData = new UnlockingDataEntity();
        newData.setAccountNumber(requestDto.getAccountNumber());
        newData.setGeneratedValue(generatedValue);
        newData.setValid(true);
        unlockingDataEntityService.save(newData);
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

        bankAccountService.removeBankAccount(bankAccountDto);
        return new ResponseEntity<>("Bank account removed", OK);

    }
}