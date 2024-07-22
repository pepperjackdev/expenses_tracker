package io.github.pepperjackdev.expensestracker.controllers;

import io.github.pepperjackdev.expensestracker.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainViewController {
    double x, y;

    @FXML BorderPane root;
    @FXML BorderPane titleBar;

    @FXML Button closeButton;
    @FXML Button maximizeButton;
    @FXML Button minimizeButton;

    @FXML StackPane dynamicFrame;

    @FXML
    void initialize() {

        // TODO: implement resizing features

        titleBar.setOnMousePressed(e -> {

            if (App.stage.isMaximized()) {
                App.stage.setMaximized(false);
                root.setStyle("-fx-background-radius: 10px");
                return;
            }

            x = e.getSceneX();
            y = e.getSceneY();
        });

        titleBar.setOnMouseDragged(e -> {
            App.stage.setX(e.getScreenX() - x);
            App.stage.setY(e.getScreenY() - y);
        });

        closeButton.setOnAction(e -> App.stage.close());

        maximizeButton.setOnAction(e -> {
            if (App.stage.isMaximized()) {
                App.stage.setMaximized(false);
                root.setStyle("-fx-background-radius: 10px");
            } else {
                App.stage.setMaximized(true);
                root.setStyle("-fx-background-radius: 0px");
            }
        });

        minimizeButton.setOnAction(e -> App.stage.setIconified(true));

        // By default behavior, the dashboard is loaded at first
        // TODO: Implement default loading of dashboard view
        
    }

}
