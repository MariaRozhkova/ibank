package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.SavedPaymentConverter;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.entity.SavedPaymentEntity;
import dev.rozhkova.ibank.repository.SavedPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedPaymentService {

    private SavedPaymentRepository savedPaymentRepository;
    private SavedPaymentConverter savedPaymentConverter;

    public void create(final SavedPaymentDto savedPaymentDto) {
        SavedPaymentEntity savedPaymentEntity = savedPaymentConverter.convertToDbo(savedPaymentDto);
        savedPaymentRepository.save(savedPaymentEntity);
    }

    public List<SavedPaymentDto> getAllSavedPayment() {
        return savedPaymentRepository.findAll().stream().map(savedPaymentConverter::convertToDto).collect(Collectors.toList());
    }
}
