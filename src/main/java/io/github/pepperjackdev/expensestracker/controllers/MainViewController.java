package io.github.pepperjackdev.expensestracker.controllers;

import java.io.IOException;

import io.github.pepperjackdev.expensestracker.App;
import io.github.pepperjackdev.expensestracker.controllers.views.Views;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

public class MainViewController {
    double x, y;

    @FXML BorderPane root;
    @FXML BorderPane titleBar;

    @FXML Button closeButton;
    @FXML Button maximizeButton;
    @FXML Button minimizeButton;

    @FXML StackPane dynamicFrame;
    
    @FXML ButtonBar navigationBar;
    @FXML Button dashboardButton;

    private boolean isMaximized = false;

    @FXML
    void initialize() {

        // TODO: implement resizing features

        // -----------------------------------------------------
        // Window features
        // -----------------------------------------------------

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
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            if (isMaximized && 
                (App.stage.getWidth() == bounds.getWidth() && App.stage.getHeight() == bounds.getHeight())) {
                App.stage.setWidth(600);
                App.stage.setHeight(370);
                App.stage.centerOnScreen();
                isMaximized = false;
                root.setStyle("-fx-background-radius: 10px");
            } else {
                App.stage.setX(bounds.getMinX());
                App.stage.setY(bounds.getMinY());
                App.stage.setWidth(bounds.getWidth());
                App.stage.setHeight(bounds.getHeight());
                isMaximized = true;
                root.setStyle("-fx-background-radius: 0px");
            }
        });

        minimizeButton.setOnAction(e -> App.stage.setIconified(true));

        // -----------------------------------------------------
        // Initializing views
        // -----------------------------------------------------

        // By default behavior, the dashboard is loaded at first
        loadViewIntoDynamicFrame(Views.DASHBOARD, dashboardButton);

        // -----------------------------------------------------
        // Buttons behaviour
        // -----------------------------------------------------

        // Every button without an id hasn't (yet) an fxml view associated with it: it should be disabled
        navigationBar.getChildrenUnmodifiable().forEach(n -> {
            if (n instanceof Button button && button.getId() == null) {
                button.setTooltip(new Tooltip("This view hasn't been initialized yet..."));
                
            }
        });

        // Buttons targetted here have an id

        dashboardButton.setOnAction(e -> {
            loadViewIntoDynamicFrame(Views.DASHBOARD, dashboardButton);
        });
        
    }

    void loadViewIntoDynamicFrame(Views view) {
        try {
            Node node = App.loadFXML(view.toString());
            dynamicFrame.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    void loadViewIntoDynamicFrame(Views view, Button associatedButton) {
        loadViewIntoDynamicFrame(view);

        // let's reset the color of navbar buttons
        navigationBar.getChildrenUnmodifiable().forEach(n -> {
            if (n instanceof Button button) {
                button.setStyle("-fx-text-fill: #778DA9");
            }
        });

        associatedButton.setStyle("-fx-text-fill: #FFFFFF");
    }
}
