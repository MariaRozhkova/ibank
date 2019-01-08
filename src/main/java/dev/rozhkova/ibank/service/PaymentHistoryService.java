package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.PaymentHistoryConverter;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.PaymentHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final PaymentHistoryConverter paymentHistoryConverter;
    private final BankAccountService bankAccountService;
    private final BankCardService bankCardService;
    private final UserService userService;

    public List<PaymentHistoryDto> getAllPaymentHistory() throws UserException{
        return paymentHistoryRepository.findAll().stream().map(paymentHistoryConverter::convertToDto).collect(Collectors.toList());
    }

    public List<PaymentHistoryDto> getAllPaymentHistoryByUserId(final Long id) throws UserException{
        return paymentHistoryRepository.findByUserId(id).stream().map(paymentHistoryConverter::convertToDto).collect(Collectors.toList());
    }

    public List<PaymentHistoryDto> getAllPaymentHistoryByBankCardNumber(final String number) throws UserException{
        return paymentHistoryRepository.findByBankCardCardNumber(number).stream().map(paymentHistoryConverter::convertToDto).collect(Collectors.toList());
    }

    public Boolean makePayment(final Long userId, final PaymentHistoryDto paymentHistoryDto) throws UserException{
        final PaymentHistoryEntity paymentHistoryEntity = paymentHistoryConverter.convertToDbo(paymentHistoryDto);
        paymentHistoryEntity.setUser(userService.getUserEntityById(userId));
        final BankAccountEntity bankAccountEntity = bankCardService.getBankAccountByCardNumber(paymentHistoryDto.getBankCard().getCardNumber());
        final Double moneyOnAccount = bankAccountEntity.getMoneyAmount();
        final Double moneyAfterOperation = moneyOnAccount - paymentHistoryDto.getMoneyAmount();
        if (moneyOnAccount >= 0 & moneyAfterOperation >= 0) {
            bankAccountService.updateMoneyAmount(moneyAfterOperation, bankAccountEntity.getId());
            final UserEntity userEntity = userService.getUserEntityById(userId);
            paymentHistoryRepository.save(paymentHistoryEntity);
            return true;
        } else {
            return false;
        }
    }

    public PaymentHistoryDto getPaymentHistoryOperationById(final Long id) {
        return paymentHistoryConverter.convertToDto(paymentHistoryRepository.getOne(id));

    }
}