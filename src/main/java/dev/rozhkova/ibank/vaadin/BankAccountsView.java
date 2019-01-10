package dev.rozhkova.ibank.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.BankAccountRepository;
import dev.rozhkova.ibank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = BankAccountsView.BANK_ACCOUNTS)
public class BankAccountsView extends VerticalLayout implements View {

    public static final String BANK_ACCOUNTS = "bankAccounts";

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @PostConstruct
    void init() throws UserException {
        addComponent(new Label("This is a bank account view"));
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
