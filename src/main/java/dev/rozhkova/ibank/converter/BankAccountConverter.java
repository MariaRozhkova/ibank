package dev.rozhkova.ibank.converter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.BankCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountConverter implements DtoDboConverter<BankAccountDto, BankAccountEntity> {

    private final BankCardConverter bankCardConverter;

    @Autowired
    public BankAccountConverter(final BankCardConverter bankCardConverter) {
        this.bankCardConverter = bankCardConverter;
    }

    @Override
    public BankAccountDto convertToDto(final BankAccountEntity dbo) {
        final BankAccountDto bankAccountDto = new BankAccountDto();
        final List<BankCardDto> bankCardDtos = bankCardConverter.convertToDto(dbo.getBankCardEntity());
        bankAccountDto.setMoneyAmount(dbo.getMoneyAmount());
        bankAccountDto.setAccountNumber(dbo.getAccountNumber());
        bankAccountDto.setBankCardEntity(bankCardDtos);
        bankAccountDto.setEnabled(dbo.getEnabled());
        return bankAccountDto;
    }

    @Override
    public BankAccountEntity convertToDbo(final BankAccountDto dto) {
        final BankAccountEntity bankAccountEntity = new BankAccountEntity();
        final List<BankCardEntity> bankCardEntities = bankCardConverter.convertToDbo(dto.getBankCardEntity());
        bankAccountEntity.setMoneyAmount(dto.getMoneyAmount());
        bankAccountEntity.setAccountNumber(dto.getAccountNumber());
        bankAccountEntity.setBankCardEntity(bankCardEntities);
        bankAccountEntity.setEnabled(dto.getEnabled());
        return bankAccountEntity;
    }

    public List<BankAccountEntity> convertToDbo(final List<BankAccountDto> dto) {
        final List<BankAccountEntity> bankAccountEntities = new ArrayList<>();
        dto.forEach(bankAccountDto -> bankAccountEntities.add(convertToDbo(bankAccountDto)));
        return bankAccountEntities;
    }

    public List<BankAccountDto> convertToDto(final List<BankAccountEntity> dbo) {
        final List<BankAccountDto> bankAccountDtos = new ArrayList<>();
        dbo.forEach(bankAccountEntity -> bankAccountDtos.add(convertToDto(bankAccountEntity)));
        return bankAccountDtos;
    }
}