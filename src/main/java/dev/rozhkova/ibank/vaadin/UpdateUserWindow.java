package dev.rozhkova.ibank.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;
import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateUserWindow extends Window {

    @Autowired
    UserConverter userConverter;

    private final FormLayout updateUserForm = new FormLayout();
    private final HorizontalLayout buttonsLayout = new HorizontalLayout();
    private final TextField firstNameTextField = new TextField("First name");
    private final TextField lastNameTextFiled = new TextField("Last name");
    private final TextField patronymicTextFiled = new TextField("patronymic");
    private final TextField passportNumberTextFiled = new TextField("passport number");
    private final TextField emailTextFiled = new TextField("email");
    private final TextField loginTextFiled = new TextField("login");
    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    private Binder<UserDto> userDtoBinder = new Binder<>();

    public UpdateUserWindow(final UserDto user, UserService userService) {
        super("Update user");
        center();
        setModal(true);

        final VerticalLayout mainLayout = new VerticalLayout();
        this.setContent(mainLayout);
        mainLayout.addComponent(updateUserForm);
        mainLayout.addComponent(buttonsLayout);

        userDtoBinder.readBean(user);
        userDtoBinder.forField(firstNameTextField)
            .bind(UserDto::getFirstName, UserDto::setFirstName);
        userDtoBinder.forField(lastNameTextFiled)
            .bind(UserDto::getLastName, UserDto::setLastName);
        userDtoBinder.forField(patronymicTextFiled)
            .bind(UserDto::getPatronymic, UserDto::setPatronymic);
        userDtoBinder.forField(passportNumberTextFiled)
            .bind(UserDto::getPassportNumber, UserDto::setPassportNumber);
        userDtoBinder.forField(emailTextFiled)
            .bind(UserDto::getEmail, UserDto::setEmail);
        userDtoBinder.forField(loginTextFiled)
            .bind(UserDto::getLogin, UserDto::setLogin);

        updateUserForm.addComponent(firstNameTextField);
        updateUserForm.addComponent(lastNameTextFiled);
        updateUserForm.addComponent(patronymicTextFiled);
        updateUserForm.addComponent(passportNumberTextFiled);
        updateUserForm.addComponent(emailTextFiled);
        updateUserForm.addComponent(loginTextFiled);

        buttonsLayout.addComponent(saveButton);
        buttonsLayout.addComponent(cancelButton);


        saveButton.addClickListener(clickEvent -> {
            try {
                userDtoBinder.writeBean(user);
                userService.updateUser(user.getId(), user);
            } catch (ValidationException e) {
                Notification.show("error");
            } catch (UserException e) {
                e.printStackTrace();
            }
            this.close();
        });
        cancelButton.addClickListener(clickEvent -> {
            userDtoBinder.readBean(user);
            this.close();
        });

    }
}