package com.menu.interactivemenu;

import Scenarios.LoginScenario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

public class MenuController
{

    @FXML
    private Menu menu;

    public void initialize()
    {
//        MenuItem item1 = new MenuItem("Withdrawal");
//        item1.setOnAction(e ->
//        {
//            String[] userInput = getUserInput("input account id and amount").split(" ");
//            var accountId = Integer.parseInt(userInput[0]);
//            var amount = new BigDecimal(userInput[1]);
//
//            try
//            {
//                showAlert(new AccountScenario().Withdrawal(amount, accountId).toString());
//            }
//            catch (ShortageOfFundsException ex)
//            {
//                showAlert("Not enough funds for withdrawal");
//            }
//        });

        Menu submenu = new Menu("Подменю");

        MenuItem item2 = new MenuItem("Пункт 2");

        item2.setOnAction(e -> showAlert(getUserInput("Введите текст для пункта 2")));

        MenuItem item3 = new MenuItem("Пункт 3");

        item3.setOnAction(e -> showAlert(new LoginScenario().Login()));

        submenu.getItems().addAll(item2, item3);
        menu.getItems().addAll(submenu);
    }

    private String getUserInput(String prompt)
    {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Ввод пользователя");
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);

        return dialog.showAndWait().orElse("");
    }

    private void showAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}