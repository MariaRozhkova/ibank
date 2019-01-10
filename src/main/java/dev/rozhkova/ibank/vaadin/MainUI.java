package dev.rozhkova.ibank.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import dev.rozhkova.ibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI
@Theme("valo")
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay {

    private Panel springViewDisplay;
    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private VerticalLayout buttonsLayout = new VerticalLayout();

    @Autowired
    private UserService userService;

    @Override
    protected void init(VaadinRequest request) {
        horizontalLayout.setSizeFull();
        setContent(horizontalLayout);
        buttonsLayout.setWidth("100px");
        horizontalLayout.addComponent(buttonsLayout);

        buttonsLayout.addComponent(createNavigationButton("Users",
                                                          UsersView.USERS));
        buttonsLayout.addComponent(createNavigationButton("Bank accounts",
                                                          BankAccountsView.BANK_ACCOUNTS));

        springViewDisplay = new Panel();
        springViewDisplay.setWidth("900px");
        horizontalLayout.addComponent(springViewDisplay);
    }

    private Button createNavigationButton(final String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(
            event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(final View view) {
        springViewDisplay.setContent((Component) view);
    }
}




