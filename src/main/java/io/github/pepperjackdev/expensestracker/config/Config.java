package io.github.pepperjackdev.expensestracker.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

import io.github.pepperjackdev.expensestracker.config.budget.Budget;

/**
 * Configuration class for the application that stores all the user preferences and settings.
 * This class saves its contents on a serialized file.
 */
public class Config 
    implements Serializable {

    private final String path;
    private Budget budget;

    private Config(String path) {
        this.path = path;
        this.budget = new Budget( // FIXME -> Bad idea to hardcode the budget
            Duration.ofDays(LocalDate.now().lengthOfMonth()),
            1000
        );
    }
    
    public static Config fromSerializedDataOrDefault(String path) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            Config config = (Config) objectInputStream.readObject();
            return config;
        } catch (IOException | ClassNotFoundException e) {
            return new Config(path);
        }
    }

    public void saveToSerializedFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Access budget settings...
    public Budget getBudget() {
        return budget;
    }
}
