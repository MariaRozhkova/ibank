package dev.rozhkova.ibank.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@SpringUI
@Theme("valo")
public class ChatUI extends UI {
    @Autowired
    private SpringNavigator navigator;

    private HorizontalLayout menuLayout;

    @Autowired
    UserService userService;

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        final Label title = new Label("menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button view1 = new Button("View 1", e->getNavigator().navigateTo("vew1"));
        view1.addStyleNames(ValoTheme.MENU_ITEM, ValoTheme.BUTTON_LINK);
        Button view2 = new Button("View 2", e->getNavigator().navigateTo("vew2"));
        view2.addStyleNames(ValoTheme.MENU_ITEM, ValoTheme.BUTTON_LINK);

        CssLayout menu = new CssLayout(title, view1, view2);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        CssLayout viewContainer = new CssLayout();

        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        setContent(mainLayout);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", DefaultView.class);
        navigator.addView("view1", View1.class);
        navigator.addView("view2", View2.class);


        /*try {
            final Grid<UserDto> grid = new Grid<>();
            grid.addColumn(UserDto::getId).setCaption("id");
            grid.addColumn(UserDto::getFirstName).setCaption("first name");
            grid.addColumn(UserDto::getLastName).setCaption("last name");
            grid.addColumn(UserDto::getPatronymic).setCaption("patronymic");
            grid.addColumn(UserDto::getPassportNumber).setCaption("passport no");
            grid.addColumn(UserDto::getEmail).setCaption("email");
            grid.addColumn(UserDto::getEnabled).setCaption("enabled");
            grid.addColumn(UserDto::getBankAccount).setCaption("bank accounts");
            setContent(grid);
            grid.setSizeFull();
            grid.setItems(userService.getAllUsers());
        } catch (final UserException e) {
            e.printStackTrace();
        }*/
    }
}
