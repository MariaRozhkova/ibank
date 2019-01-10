package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import dev.rozhkova.ibank.entity.SavedPaymentEntity;
import dev.rozhkova.ibank.repository.PaymentHistoryRepository;
import dev.rozhkova.ibank.repository.SavedPaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class SavedPaymentService {
    private SavedPaymentRepository savedPaymentRepository;
    private SavedPaymentConverter savedPaymentConverter;
    private PaymentHistoryRepository paymentHistoryRepository;

    public boolean savePayment(final PaymentHistoryDto paymentHistoryDto) {
        SavedPaymentDto savedPaymentDto = new SavedPaymentDto();
        savedPaymentDto.setBankCard(paymentHistoryDto.getBankCard());
        savedPaymentDto.setMoneyAmount(paymentHistoryDto.getMoneyAmount());
        savedPaymentDto.setPaymentAccount(paymentHistoryDto.getPaymentAccount());
        savedPaymentDto.setPaymentOperation(paymentHistoryDto.getPaymentOperation());

        List<PaymentHistoryEntity> paymentHistoryRepositoryByBankCardCardNumber
            = paymentHistoryRepository.findByBankCardCardNumber(paymentHistoryDto.getBankCard().getCardNumber());

        if (paymentHistoryRepositoryByBankCardCardNumber.size() == 0) {
            return false;
        }

        PaymentHistoryEntity paymentHistoryEntity = paymentHistoryRepositoryByBankCardCardNumber.get(0);

        SavedPaymentEntity savedPaymentEntity = savedPaymentConverter.convertToDbo(savedPaymentDto);
        savedPaymentEntity.setUser(paymentHistoryEntity.getUser());

        savedPaymentRepository.save(savedPaymentEntity);

        return true;
    }

    public List<SavedPaymentDto> getAllSavedPaymentByUserId(final long id) {
        List<SavedPaymentDto> savedPaymentDtos = savedPaymentRepository.findByUserId(id).stream().map(savedPaymentConverter::convertToDto).collect(Collectors.toList());
        return savedPaymentDtos;
    }

    public List<SavedPaymentDto> getAllSavedPayment() {
        return savedPaymentRepository.findAll().stream().map(savedPaymentConverter::convertToDto).collect(
            Collectors.toList());
    }

    public SavedPaymentDto getSavedPaymentById(final long id) {
        return savedPaymentConverter.convertToDto(savedPaymentRepository.getOne(id));
    }
}