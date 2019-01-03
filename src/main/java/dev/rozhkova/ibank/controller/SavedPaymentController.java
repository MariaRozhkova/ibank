package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.SavedPaymentService;
import dev.rozhkova.ibank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class SavedPaymentController {
    private final SavedPaymentService savedPaymentService;
    private final UserService userService;

    @GetMapping("/{userId}/saved")
    public List<SavedPaymentDto> getAllSavedPayments(@PathVariable final Long userId) {
        List<SavedPaymentDto> list = new ArrayList<>();

        try {
            final UserDto userDto = userService.getUserById(userId);

            for (SavedPaymentDto saved : savedPaymentService.getAllSavedPayment()) {

                if (saved.getUser().equals(userDto)) {
                    list.add(saved);
                }
            }
        } catch (UserException ex) {}

        return list;
    }
}
