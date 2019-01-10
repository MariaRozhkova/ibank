/*
package dev.rozhkova.ibank.vaadin;

import com.vaadin.ui.*;
import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateBankCardDialog extends Window {

    @Autowired
    BankCardConverter userConverter;

    public UpdateBankCardDialog(final UserDto userAccount, UserRepository userRepository) {
        super("Update user account"); // Set window caption
        center();
        setModal(true);
        VerticalLayout layout = new VerticalLayout();
        TextField firstName = new TextField("First name: ", userAccount.getFirstName());
        TextField lastName = new TextField("Last name: ", userAccount.getLastName());
        layout.addComponents(firstName, lastName, new Button("Submit", event -> {
            userAccount.setFirstName(firstName.getValue().trim());
            userAccount.setLastName(lastName.getValue().trim());
            */
/*if (password.getValue().trim().length() != 0) {
                System.out.println("password: " + password.getValue().trim());
                userAccount.setLastName(password.getValue());
            }*//*

            userRepository.save(userConverter.convertToDbo(userAccount));
            close();
        }), new Button("Cancel", event -> close()));
        setContent(layout);
    }

    */
/*Binder<UserAccount> binder = new Binder<>();

    VerticalLayout layout = new VerticalLayout();
    TextField username = new TextField("Username: ");
    TextField password = new TextField("Password: ");
    // Shorthand for cases without extra configuration

        binder.bind(username, UserAccount::getUsername, UserAccount::setUsername);
        binder.bind(password, UserAccount::getPassword, UserAccount::setPassword);
        binder.readBean(userAccount);
        layout.addComponents(username, password, new Button("Submit", event -> {
        try {
            binder.writeBean(userAccount);
            userRepository.save(userAccount);
        } catch (ValidationException e) {
            Notification.show("Person could not be saved, " +
                              "please check error messages for each field.");
        }
        close();
    }), new Button("Cancel", event -> {
        binder.readBean(userAccount);
        close();
    }));*//*

}*/
