package com.menu.interactivemenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApplication extends Application
{

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        scene.getStylesheets().add(MenuApplication.class.getResource("/ThemesForMenu/dark-theme.css").toExternalForm());
        stage.setTitle("submenu");
        stage.setScene(scene);
        stage.show();
    }
}