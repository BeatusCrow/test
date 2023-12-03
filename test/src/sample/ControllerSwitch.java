package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerSwitch extends Main {

    @FXML
    public TextField name;

    @FXML
    public TextField soname;

    @FXML
    public TextField job;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void onClickEntry(ActionEvent event) throws IOException {
        String name = this.name.getText();
        String soname = this.soname.getText();
        String job = this.job.getText();

        if (!Objects.equals(name, "") && !Objects.equals(soname, "") && !Objects.equals(job, "")) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/windows/TestWindow.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println(name + soname + job);
        }
        else {
            System.out.println("Ошибка");
        }
    }
}
