package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.PaymentOperationConverter;
import dev.rozhkova.ibank.dto.PaymentOperationDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.PaymentOperationEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.PaymentOperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentOperationService {

    private final PaymentOperationRepository paymentOperationRepository;
    private final PaymentOperationConverter paymentOperationConverter;

    public void create(final PaymentOperationDto paymentOperationDto) throws UserException {
        final PaymentOperationEntity paymentOperationEntity = paymentOperationConverter.convertToDbo(paymentOperationDto);
        paymentOperationRepository.save(paymentOperationEntity);
    }

    public List<PaymentOperationDto> getAllPaymentOperation() throws UserException {
        return paymentOperationRepository.findAll().stream().map(paymentOperationConverter::convertToDto).collect(Collectors.toList());
    }

    public PaymentOperationDto getPaymentOperationById(final Long id) throws UserException {
        return paymentOperationConverter.convertToDto(paymentOperationRepository.findById(id).orElse(new PaymentOperationEntity()));
    }

    public void removePaymentOperationById(final Long id) throws UserException {
        paymentOperationRepository.deleteById(id);
    }
}