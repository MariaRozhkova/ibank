package dev.rozhkova.ibank.vaadin;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import dev.rozhkova.ibank.dto.BankAccountDto;

import java.util.ArrayList;
import java.util.List;

public class BankAccountField extends CustomField<List<BankAccountDto>> {
    private List<BankAccountDto> value;
    private List<BankAccountDto> bankAccountDtos;

    private VerticalLayout verticalLayout = new VerticalLayout();
    private final ComboBox<String> bankAccountComboBox = new ComboBox<>();
    private final Button addButton = new Button(VaadinIcons.PLUS);
    private ListDataProvider<BankAccountDto> bankAccountDataProvider;

    public BankAccountField(final List<BankAccountDto> value) {
        this.value = new ArrayList<>(value);
    }

    @Override
    protected Component initContent() {
        bankAccountDataProvider = DataProvider.ofCollection(bankAccountDtos);

       //bankAccountComboBox.setDataProvider(bankAccountDataProvider);

        //verticalLayout.addComponent(new HorizontalLayout(genresComboBox, addButton));
        addButton.setEnabled(false);
        return null;
    }

    @Override
    protected void doSetValue(final List<BankAccountDto> bankAccountDtos) {
        this.value = new ArrayList<>(value);
    }

    @Override
    public List<BankAccountDto> getValue() {
        return value;
    }
}