package io.github.pepperjackdev.expensestracker;

import java.io.IOException;
import java.time.LocalDate;

import io.github.pepperjackdev.expensestracker.expenses.Expenses;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static io.github.pepperjackdev.expensestracker.constants.Paths.DASHBOARD_FXML;

public class App extends Application {

    public static final Expenses expenses; 
    private static Scene scene;

    static {
        expenses = new Expenses("data.db");
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(DASHBOARD_FXML));
        stage.setScene(scene);
        stage.show();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // let's feed the database with some data
        expenses.addNewExpense(13.5, "Lunch at restaurant", LocalDate.of(2024, 2, 13), DASHBOARD_FXML);
        launch();
    }
}
