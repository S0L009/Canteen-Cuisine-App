package com.example.fxplswork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class apln extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("asgn.fxml")));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("I'm trying... -- Mainpage");
        stage.setFullScreen(false);
        stage.show();
    }
}

