package io.github.pepperjackdev.expensestracker.controllers;

import io.github.pepperjackdev.expensestracker.App;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainViewController {
    double x, y;

    @FXML BorderPane titleBar;

    @FXML
    void initialized() {
        titleBar.setOnMousePressed(e -> {
            x = e.getSceneX();
            y = e.getSceneY();
        });

        titleBar.setOnMouseDragged(e -> {
            App.stage.setX(e.getScreenX() - x);
            App.stage.setY(e.getScreenY() - y);
        });
    }

}
