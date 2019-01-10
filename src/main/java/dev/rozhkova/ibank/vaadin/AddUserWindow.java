package dev.rozhkova.ibank.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.UserService;

public class AddUserWindow extends Window {

    private Binder<UserEntity> userEntityBinder = new Binder<>();

    private final TextField firstNameTextField = new TextField("First name");
    private final TextField lastNameTextFiled = new TextField("Last name");
    private final TextField patronymicTextFiled = new TextField("patronymic");
    private final TextField passportNumberTextFiled = new TextField("passport number");
    private final TextField emailTextFiled = new TextField("email");
    private final TextField loginTextFiled = new TextField("login");
    private final PasswordField passwordTextFiled = new PasswordField("password");


    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    private UserEntity userEntity = new UserEntity();

    public AddUserWindow(final UserService userService) {
        super("Add new user");
        VerticalLayout mainLayout = new VerticalLayout();
        this.setContent(mainLayout);
        FormLayout addUserForm = new FormLayout();
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        mainLayout.addComponent(addUserForm);
        mainLayout.addComponent(buttonsLayout);


        passwordTextFiled.setMaxLength(15);
        updateCaption(0);
        passwordTextFiled.addValueChangeListener(event -> updateCaption(event.getValue().length()));

        userEntityBinder.readBean(userEntity);
        userEntityBinder.forField(firstNameTextField)
            .bind(UserEntity::getFirstName, UserEntity::setFirstName);
        userEntityBinder.forField(lastNameTextFiled)
            .bind(UserEntity::getLastName, UserEntity::setLastName);
        userEntityBinder.forField(patronymicTextFiled)
            .bind(UserEntity::getPatronymic, UserEntity::setPatronymic);
        userEntityBinder.forField(passportNumberTextFiled)
            .bind(UserEntity::getPassportNumber, UserEntity::setPassportNumber);
        userEntityBinder.forField(emailTextFiled)
            .bind(UserEntity::getEmail, UserEntity::setEmail);
        userEntityBinder.forField(loginTextFiled)
            .bind(UserEntity::getLogin, UserEntity::setLogin);
        userEntityBinder.forField(passwordTextFiled)
            .bind(UserEntity::getPassword, UserEntity::setPassword);

        addUserForm.addComponent(firstNameTextField);
        addUserForm.addComponent(lastNameTextFiled);
        addUserForm.addComponent(patronymicTextFiled);
        addUserForm.addComponent(passportNumberTextFiled);
        addUserForm.addComponent(emailTextFiled);
        addUserForm.addComponent(loginTextFiled);
        addUserForm.addComponent(passwordTextFiled);

        buttonsLayout.addComponent(saveButton);
        buttonsLayout.addComponent(cancelButton);


        saveButton.addClickListener(clickEvent -> {
            try {
                userEntityBinder.writeBean(userEntity);
                userService.create(userEntity);
            } catch (ValidationException e) {
                Notification.show("error");
            } catch (UserException e) {
                e.printStackTrace();
            }
            this.close();
        });
        cancelButton.addClickListener(clickEvent -> {
            userEntityBinder.readBean(userEntity);
            this.close();
        });

    }

    private void updateCaption(final int textLength) {
        final StringBuilder builder = new StringBuilder();
        builder.append("password ");
        builder.append(textLength);
        if(passwordTextFiled.getMaxLength() >= 0) {
            builder.append("/").append(passwordTextFiled.getMaxLength());
        }
        builder.append(" characters ");
        passwordTextFiled.setCaption(builder.toString());
    }
}