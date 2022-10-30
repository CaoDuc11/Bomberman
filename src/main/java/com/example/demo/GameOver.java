package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOver {
    private Stage stage;
    public void Over(javafx.event.ActionEvent over) throws IOException {
        MenuTest Test = new MenuTest();
        Test.Test(over);
    }
}
