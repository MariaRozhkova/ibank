/*
package dev.rozhkova.ibank.vaadin;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import dev.rozhkova.ibank.converter.BankCardConverter;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.BankCardRepository;
import dev.rozhkova.ibank.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = BankCardView.BANK_CARDS)
public class BankCardView extends VerticalLayout implements View {

    public static final String BANK_CARDS = "bankCars";

    @Autowired
    BankCardService bankCardService;

    @Autowired
    BankCardRepository bankCardRepository;

    @Autowired
    BankCardConverter bankCardConverter;

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }

    @PostConstruct
    void init() throws UserException {
        addComponent(new Label("This is a bank card view"));

        DataProvider<BankCardDto, Void> dataProvider = DataProvider.fromCallbacks(
            query -> {
                long offset = query.getOffset();
                int limit = query.getLimit();
                List<BankCardDto> bankCardDtos = null;
                try {
                    bankCardDtos = bankCardService.getAllBankCard();
                } catch (UserException e) {
                    e.printStackTrace();
                }
                return bankCardDtos.stream();
            },
            query -> (int) bankCardRepository.count());

        Grid<BankCardDto> grid = new Grid<>();
        grid.addColumn(BankCardDto::getId).setCaption("id");
        grid.addColumn(BankCardDto::getCardNumber).setCaption("card number");
        grid.addColumn(BankCardDto:: getCardHolderName).setCaption("card holder");
        */
/*grid.addColumn(UserDto::getPatronymic).setCaption("patronymic");
        grid.addColumn(UserDto::getEmail).setCaption("email");
        grid.addColumn(UserDto::getLogin).setCaption("login");
        grid.addColumn(UserDto::getBankAccount).setCaption("bank account");*//*

        //setContent(grid);
        //grid.setSizeFull();
        grid.setItems(bankCardService.getAllBankCard());

        HorizontalLayout buttonLayout = new HorizontalLayout();
        */
/*Button addPersonButton = new Button("Add user account",
                                            clickEvent -> {
                                                UserEntity userEntity = new UserEntity();
                                                userEntity.setFirstName("fbjg");
                                                userEntity.setLastName("jhsbfd");
                                                userEntity.setPatronymic("dsfd");
                                                userEntity.setEmail("bdfhddst@mail.ru");
                                                userEntity.setPassportNumber("wrt32345");
                                                userEntity.setLogin("sdfd");
                                                userEntity.setPassword("1243rq");
                                                userRepository.save(userEntity);
                                                dataProvider.refreshAll();
                                            });*//*


        Button modifyPersonButton = new Button("Modify bank card account",
                                               clickEvent -> {
                                                   final BankCardDto bankCardToChange = grid.getSelectionModel().getFirstSelectedItem().get();
                                                   UpdateUserDialog updateUserDialog = new UpdateUserDialog(personToChange, userRepository);
                                                   updateUserDialog.setHeight("400px");
                                                   updateUserDialog.setWidth("400px");

                                                   // Set window position.
                                                   updateUserDialog.setPositionX(200);
                                                   updateUserDialog.setPositionY(50);
                                                   UI.getCurrent().addWindow(updateUserDialog);
                                                   UserEntity tmp = userRepository.findById(personToChange.getId()).get();
                                                   personToChange.setFirstName(tmp.getFirstName());
                                                   personToChange.setLastName(tmp.getLastName());
                                                   userRepository.save(userConverter.convertToDbo(personToChange));
                                                   grid.getSelectionModel().deselectAll();
                                                   dataProvider.refreshAll();
                                               });


        */
/*Button deletePersonButton = new Button("Delete user account",
                                               clickEvent -> {
                                                   UserDto userDto = grid.getSelectionModel().getFirstSelectedItem().get();
                                                   userRepository.deleteById(userDto.getId());
                                                   dataProvider.refreshAll();
                                               });*//*


        //buttonLayout.addComponents(addPersonButton, modifyPersonButton, deletePersonButton);
        buttonLayout.addComponents(modifyPersonButton);
        addComponent(buttonLayout);
        addComponent(grid);
    }
}*/
