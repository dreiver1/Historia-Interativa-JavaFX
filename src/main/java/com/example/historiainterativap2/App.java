package com.example.historiainterativap2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Historia historia;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        historia = new Historia();
        historia.recuperarHistoria();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        Scene scene = new Scene(loader.load());
        AppController appController = loader.getController();
        appController.setHistoria(historia);
        stage.setTitle("Historia interativa!");
        stage.setScene(scene);
        stage.show();
    }
}
