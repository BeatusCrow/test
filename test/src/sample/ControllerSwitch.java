package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import questions.ControllerQuestions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ControllerSwitch {

    @FXML
    public TextField name;

    @FXML
    public TextField soname;

    @FXML
    public TextField job;

    private Stage stage;
    private static Scene scene;
    private Parent root;

    private static int number_questions = 0;

    public static Text text_quest;

    @FXML
    public void onClickEntry(ActionEvent event) throws IOException {
        String name = this.name.getText();
        String soname = this.soname.getText();
        String job = this.job.getText();

        if (!Objects.equals(name, "") && !Objects.equals(soname, "") && !Objects.equals(job, "")) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/windows/TestWindow.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            text_quest = (Text) scene.lookup("#text_question");
            stage.setScene(scene);
            stage.show();
            System.out.println(name + soname + job);
            number_questions++;
            System.out.println(number_questions);
            SwitchTextQuestion();
            SwitchTextAnswer1();
            SwitchTextAnswer2();
            SwitchTextAnswer3();
            SwitchTextAnswer4();
        }
        else {
            System.out.println("Ошибка");
        }
    }

    @FXML
    public void onClickNext(ActionEvent event) throws IOException {
        number_questions = number_questions + 1;
        SwitchTextQuestion();
        SwitchTextAnswer1();
        SwitchTextAnswer2();
        SwitchTextAnswer3();
        SwitchTextAnswer4();
    }

    @FXML
    public void onClickPast(ActionEvent event) throws IOException {
        number_questions--;
        SwitchTextQuestion();
        SwitchTextAnswer1();
        SwitchTextAnswer2();
        SwitchTextAnswer3();
        SwitchTextAnswer4();
    }

    public void SwitchTextQuestion() throws FileNotFoundException {
        String text = ControllerQuestions.TextQuestions(number_questions);
        text_quest.setText(text);
    }

    public void SwitchTextAnswer1() throws FileNotFoundException {
        String text = ControllerQuestions.TextAnswer1(number_questions);
        RadioButton text_quest = (RadioButton) scene.lookup("#radio_button_1");
        text_quest.setText(text);
    }

    public void SwitchTextAnswer2() throws FileNotFoundException {
        String text = ControllerQuestions.TextAnswer2(number_questions);
        RadioButton text_quest = (RadioButton) scene.lookup("#radio_button_2");
        text_quest.setText(text);
    }

    public void SwitchTextAnswer3() throws FileNotFoundException {
        String text = ControllerQuestions.TextAnswer3(number_questions);
        RadioButton text_quest = (RadioButton) scene.lookup("#radio_button_3");
        text_quest.setText(text);
    }

    public void SwitchTextAnswer4() throws FileNotFoundException {
        String text = ControllerQuestions.TextAnswer4(number_questions);
        RadioButton text_quest = (RadioButton) scene.lookup("#radio_button_4");
        text_quest.setText(text);
    }
}
