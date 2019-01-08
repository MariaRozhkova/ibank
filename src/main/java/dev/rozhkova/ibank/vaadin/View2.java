package dev.rozhkova.ibank.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;

public class View2 extends Composite implements View {
    public View2() {
        setCompositionRoot(new Label("This is view 2"));
    }
}