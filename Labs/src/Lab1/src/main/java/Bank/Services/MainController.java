package Bank.Services;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MainController {

    @FXML
    private Menu menu;

    public void initialize() {
        MenuItem item1 = new MenuItem("Пункт 1");
        item1.setOnAction(e -> showAlert("Вы выбрали пункт 1"));

        Menu submenu = new Menu("Подменю");
        MenuItem item2 = new MenuItem("Пункт 2");
        item2.setOnAction(e -> showAlert("Вы выбрали пункт 2"));
        MenuItem item3 = new MenuItem("Пункт 3");
        item3.setOnAction(e -> showAlert("Вы выбрали пункт 3"));
        submenu.getItems().addAll(item2, item3);

        menu.getItems().addAll(item1, submenu);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
