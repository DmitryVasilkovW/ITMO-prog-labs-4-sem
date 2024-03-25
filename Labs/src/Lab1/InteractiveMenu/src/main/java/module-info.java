module com.menu.interactivemenu
{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires Lab1;

    opens com.menu.interactivemenu to javafx.fxml;
    exports com.menu.interactivemenu;
    opens Scenarios to javafx.fxml;
    exports Scenarios;
}

