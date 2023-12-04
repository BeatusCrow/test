package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import questions.ControllerQuestions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

/*
 The class responsible for changing windows during authorization, changing questions when using different buttons.
 */
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
    private static Text text_quest;

    /*
     This method checks whether the authorization fields are full and loads the test window accordingly.
     */
    @FXML
    public void onClickEntry(ActionEvent event) throws IOException {
        String name = this.name.getText();
        String soname = this.soname.getText();
        String job = this.job.getText();

        // We check the authorization fields for fullness
        if (!Objects.equals(name, "") && !Objects.equals(soname, "") && !Objects.equals(job, "")) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/windows/TestWindow.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            text_quest = (Text) scene.lookup("#text_question"); // We find an element on the scene that is responsible for the text of the question

            number_questions++;   // Increasing the number_questions value in order to load the questions on the first page.
            SwitchTextQuestion(); //
            SwitchTextAnswer1();  //
            SwitchTextAnswer2();  // Calling methods to update texts (question, answers to a question)
            SwitchTextAnswer3();  //
            SwitchTextAnswer4();  //
        }
        else {
            System.out.println("Ошибка"); // Later I will replace it with the appearance of a window with an error and its detailed description
        }
    }

    @FXML
    public void onClickNext(ActionEvent event) throws IOException { // The method for the button responsible for going to the next question (not the "answer" button)
        number_questions = number_questions + 1;
        SwitchTextQuestion();
        SwitchTextAnswer1();
        SwitchTextAnswer2();
        SwitchTextAnswer3();
        SwitchTextAnswer4();
    }

    @FXML
    public void onClickPast(ActionEvent event) throws IOException { // The method for the button responsible for going to the next question
        number_questions--;
        SwitchTextQuestion();
        SwitchTextAnswer1();
        SwitchTextAnswer2();
        SwitchTextAnswer3();
        SwitchTextAnswer4();
    }

    /*
     Next, there will be a group of methods responsible for changing the text of the question, answers to the question
     */
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
