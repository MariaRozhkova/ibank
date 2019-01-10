package dev.rozhkova.ibank.vaadin;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.selection.MultiSelectionEvent;
import com.vaadin.event.selection.MultiSelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringView(name = UsersView.USERS)
public class UsersView extends VerticalLayout implements View, MultiSelectionListener<UserDto> {

    public static final String USERS = "users";

    @Autowired
    private UserService userService;

    @Autowired
    UserConverter userConverter;

    private Set<UserDto> set = new HashSet<>();
    private Button modifyPersonButton;

    @PostConstruct
    void init() throws UserException {
        addComponent(new Label("Users"));

        List<UserDto> users = userService.getAllUsers();
        ListDataProvider<UserDto> dataProvider = DataProvider.ofCollection(users);

        TextField filterTextField = new TextField("Filter by lastName");
        filterTextField.setPlaceholder("name filter");
        filterTextField.addValueChangeListener(event -> {
            dataProvider.setFilter(UserDto::getLastName, name -> {
                String nameLower = name == null ? ""
                                   : name.toLowerCase(Locale.ENGLISH);
                String filterLower = event.getValue()
                    .toLowerCase(Locale.ENGLISH);
                return nameLower.contains(filterLower);
            });
        });

        Grid<UserDto> grid = new Grid<>();
        grid.addColumn(UserDto::getId).setCaption("id");
        grid.addColumn(UserDto::getFirstName).setCaption("first name");
        grid.addColumn(UserDto::getLastName).setCaption("last name");
        grid.addColumn(UserDto::getPatronymic).setCaption("patronymic");
        grid.addColumn(UserDto::getEmail).setCaption("email");
        grid.addColumn(UserDto::getLogin).setCaption("login");
        grid.addColumn(UserDto::getBankAccount).setCaption("bank account");


        grid.setItems(userService.getAllUsers());
        grid.setWidth("900px");

        grid.setDataProvider(dataProvider);

        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button addUserButton = new Button("Add new user",
                                          clickEvent -> {
                                              AddUserWindow addUserWindow = new AddUserWindow(userService);
                                              addUserWindow.setHeight("750px");
                                              addUserWindow.setWidth("450px");
                                              addUserWindow.setPositionX(300);
                                              addUserWindow.setPositionY(100);
                                              UI.getCurrent().addWindow(addUserWindow);
                                              grid.getSelectionModel().deselectAll();
                                              dataProvider.refreshAll();
                                          });

        modifyPersonButton = new Button("Edit user",
                                               clickEvent -> {
                                                   final UserDto userDto = grid.getSelectionModel().getFirstSelectedItem().get();
                                                   UpdateUserWindow
                                                       updateUserWindow = new UpdateUserWindow(userDto, userService);
                                                   updateUserWindow.setHeight("750px");
                                                   updateUserWindow.setWidth("380px");

                                                   updateUserWindow.setPositionX(300);
                                                   updateUserWindow.setPositionY(100);
                                                   UI.getCurrent().addWindow(updateUserWindow);
                                                   grid.getSelectionModel().deselectAll();
                                                   dataProvider.refreshAll();
                                               });

        Button deleteUserButton = new Button("Delete user",
                                               clickEvent -> {
                                                       grid.getSelectedItems().forEach(userDto -> {
                                                           try {
                                                               userService.removeUser(userDto.getId());
                                                           } catch (UserException e) {
                                                               e.printStackTrace();
                                                           }
                                                       });
                                                   dataProvider.refreshAll();
                                               });

        modifyPersonButton.setEnabled(false);
        deleteUserButton.setEnabled(false);

        if (grid.getSelectedItems().size() > 0) {
            modifyPersonButton.setEnabled(true);
        }

        buttonLayout.addComponents(addUserButton, modifyPersonButton, deleteUserButton);
        addComponent(buttonLayout);
        addComponent(filterTextField);
        addComponent(grid);
    }


    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    public void selectionChange(final MultiSelectionEvent<UserDto> multiSelectionEvent) {
    }
}
