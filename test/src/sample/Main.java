package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 This class is intended only for loading the start window.
 Nothing else is happening here.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/windows/EntryWindow.fxml")); // loading the start window.
        Parent root = loader.load();
        stage.setTitle("Test");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false); // I have removed the ability to stretch the window, because there are some problems with stretching the elements of the scene.
        stage.show();
    }

    public static void main(String[] args){launch(args);}
}