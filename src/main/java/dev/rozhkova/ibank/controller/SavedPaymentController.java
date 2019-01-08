package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.service.PaymentHistoryService;
import dev.rozhkova.ibank.service.SavedPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class SavedPaymentController {
    private final SavedPaymentService savedPaymentService;
    private final PaymentHistoryService paymentHistoryService;

    @GetMapping("/saved/{userId}/list")
    public List<SavedPaymentDto> getAllSavedPayments(@PathVariable final Long userId) {
        return savedPaymentService.getAllSavedPaymentByUserId(userId);
    }

    @GetMapping("/saved/list")
    public List<SavedPaymentDto> getAllSavedPayments() {
        return savedPaymentService.getAllSavedPayment();
    }

    @PostMapping("/saved/save/{id}")
    public ResponseEntity savePayment(@PathVariable final Long id) {
        final PaymentHistoryDto paymentHistoryDto = paymentHistoryService.getPaymentHistoryOperationById(id);
        if (savedPaymentService.savePayment(paymentHistoryDto)) {
            return new ResponseEntity<>("Payment record saved", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Payment record not found" ,HttpStatus.NOT_FOUND);
    }
}
