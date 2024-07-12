package io.github.pepperjackdev.expensestracker;

import java.time.LocalDate;

import io.github.pepperjackdev.expensestracker.expenses.Expenses;

/**
 * JavaFX App
 */
public class App /* extends Application */ {

    //  private static Scene scene;
    //  
    //  @Override
    //  public void start(Stage stage) throws IOException {
    //      scene = new Scene(loadFXML(null)); // TODO: Change this to the main FXML file
    //      stage.setScene(scene);
    //      stage.show();
    //  }
    //  
    //  static void setRoot(String fxml) throws IOException {
    //      scene.setRoot(loadFXML(fxml));
    //  }
    //  
    //  private static Parent loadFXML(String fxml) throws IOException {
    //      FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    //      return fxmlLoader.load();
    //  }

    public static void main(String[] args) {
        // launch(); FIXME: I'm not supposed to be commented...
        Expenses expenses = new Expenses("database.db");
        // let's add some new random expenses
        expenses.addNewExpense(10.0, "Coffee", LocalDate.now(), "Food");
        expenses.addNewExpense(20.0, "Gas", LocalDate.now(), "Transportation");
        expenses.addNewExpense(30.0, "Shoes", LocalDate.now(), "Clothing");
        expenses.addNewExpense(40.0, "Books", LocalDate.now(), "Education");
        expenses.addNewExpense(50.0, "Gym", LocalDate.now(), "Health");

        // let's print all the expenses (inform the user of what we've done)
        System.out.println("All expenses:");
        expenses.getAllExpenses().forEach(System.out::println);

        // let's print all the expenses in the "Food" category
        System.out.println("All expenses in the 'Food' category:");
        expenses.getAllExpensesOfCategory("Food").forEach(System.out::println);

        // let's print all the expenses in the "Transportation" category
        System.out.println("All expenses in the 'Transportation' category:");
        expenses.getAllExpensesOfCategory("Transportation").forEach(System.out::println);

        // let's print all the expenses in the "Clothing" category for today
        System.out.println("All expenses in the 'Clothing' category for today:");
        expenses.getAllExpensesOfCategoryAndDateRange("Clothing", LocalDate.now(), LocalDate.now()).forEach(System.out::println);

    }
}
