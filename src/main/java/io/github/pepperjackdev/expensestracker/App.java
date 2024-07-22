package io.github.pepperjackdev.expensestracker;

import static io.github.pepperjackdev.expensestracker.constants.Paths.MAIN_FXML;

import java.io.IOException;
import java.time.LocalDate;

import io.github.pepperjackdev.expensestracker.expenses.Expenses;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static final Expenses expenses; 
    public static Stage stage;
    private static Scene scene;

    static {
        expenses = new Expenses("data.db");
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        scene = new Scene(loadFXML(MAIN_FXML));
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = getFXMLLoader(fxml);
        return fxmlLoader.load();
    }

    private static FXMLLoader getFXMLLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        // let's feed the database with some data
        expenses.addNewExpense(13.5, "Dinner at restaurent", LocalDate.now() , "Food");
        launch();
    }
}
