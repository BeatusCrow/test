package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

/**
 The main class responsible for loading the authorization window.
 */
public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/windows/EntryWindow.fxml")); // loading the start window.
        Parent root = loader.load();
        stage.setTitle("Test");
        stage.setScene(new Scene(root, 600, 400, Color.TRANSPARENT));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setAlwaysOnTop(false);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}