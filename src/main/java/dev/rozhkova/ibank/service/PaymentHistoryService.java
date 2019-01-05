package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.PaymentHistoryConverter;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.PaymentHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final PaymentHistoryConverter paymentHistoryConverter;

    public void create(final PaymentHistoryDto paymentHistoryDto) throws UserException{
        final PaymentHistoryEntity paymentHistoryEntity = paymentHistoryConverter.convertToDbo(paymentHistoryDto);
        paymentHistoryRepository.save(paymentHistoryEntity);
    }

    public List<PaymentHistoryDto> getAllPaymentHistory() throws UserException{
        return paymentHistoryRepository.findAll().stream().map(paymentHistoryConverter::convertToDto).collect(Collectors.toList());
    }

    public List<PaymentHistoryDto> getAllPaymentHistoryByUserId(final Long id) throws UserException{
        return paymentHistoryRepository.findByUserId(id).stream().map(paymentHistoryConverter::convertToDto).collect(Collectors.toList());
    }

    public List<PaymentHistoryDto> getAllPaymentHistoryByBankCardNumber(final String number) throws UserException{
        return paymentHistoryRepository.findByBankCardCardNumber(number).stream().map(paymentHistoryConverter::convertToDto).collect(Collectors.toList());
    }
}