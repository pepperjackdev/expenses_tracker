package io.github.pepperjackdev.expensestracker;

import java.io.IOException;

import io.github.pepperjackdev.expensestracker.config.Config;
import io.github.pepperjackdev.expensestracker.controllers.views.Views;
import io.github.pepperjackdev.expensestracker.expenses.Expenses;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static final Config config;
    public static final Expenses expenses; 
    public static Stage stage;
    private static Scene scene;

    static {
        expenses = new Expenses("data.db");
        config = Config.fromSerializedDataOrDefault("config.dat");
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        scene = new Scene(loadFXML(Views.MAIN.toString()));
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public static Parent loadFXML(String fxml) {
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = getFXMLLoader(fxml);
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static FXMLLoader getFXMLLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {

        config.getBudget().setMonthlyBudget(900);
        System.out.println(config.getBudget().getDailyBudget());
        System.out.println(config.getBudget().toString());

        // launch the application
        launch();
        
        // save the configuration
        config.saveToSerializedFile();
    }
}
