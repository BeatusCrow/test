package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import questions.ControllerQuestions;
import questions.Questions;

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
    public RadioButton radio_button_1;
    @FXML
    public RadioButton radio_button_2;
    @FXML
    public RadioButton radio_button_3;
    @FXML
    public RadioButton radio_button_4;

    @FXML
    public ProgressIndicator progressbar;

    @FXML
    public TextField job;

    private Stage stage;
    private static Scene scene;
    private Parent root;
    private static int number_questions = 0;
    private static Text text_quest;

    private double xOffset = 0;
    private double yOffset = 0;

    public static double progress;

    public static boolean state_past_button = false;
    public static boolean state_next_button = true;

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
        if(state_next_button){
            number_questions++;
            SwitchAllText();
            SwitchStateRB(number_questions);
            ChangeStatePastButton();
            ChangeStateNextButton();
        }
    }

    @FXML
    public void onClickPast(ActionEvent event) throws IOException { // The method for the button responsible for going to the next question
        if(state_past_button){
            number_questions--;
            SwitchAllText();
            SwitchStateRB(number_questions);
            ChangeStatePastButton();
            ChangeStateNextButton();
        }
    }


    public void SwitchStateRB(int number_question){
        int true_answer = ControllerQuestions.getQuestionList(number_question);
        switch (true_answer){
            case (1) -> radio_button_1.setSelected(true);
            case (2) -> radio_button_2.setSelected(true);
            case (3) -> radio_button_3.setSelected(true);
            case (4) -> radio_button_4.setSelected(true);
        }
    }

    public void SwitchAllText() throws FileNotFoundException {
        SwitchTextQuestion();
        SwitchTextAnswer1();
        SwitchTextAnswer2();
        SwitchTextAnswer3();
        SwitchTextAnswer4();

        radio_button_1.setSelected(false);
        radio_button_2.setSelected(false);
        radio_button_3.setSelected(false);
        radio_button_4.setSelected(false);
    }

    @FXML
    public void onClickAnswer(ActionEvent actionEvent) throws IOException {
        System.out.println(ControllerQuestions.checkAnswer());
        if(number_questions==10){
            if(!ControllerQuestions.checkAnswer()){
                number_questions = ControllerQuestions.getNumberAnswer();
            }
        }

        if(radio_button_1.isSelected()){
            ControllerQuestions.setQuestionList(number_questions, 1);
            OnClickSwitch();
        } else if(radio_button_2.isSelected()){
            ControllerQuestions.setQuestionList(number_questions, 2);
            OnClickSwitch();
        } else if(radio_button_3.isSelected()){
            ControllerQuestions.setQuestionList(number_questions, 3);
            OnClickSwitch();
        } else if(radio_button_4.isSelected()){
            ControllerQuestions.setQuestionList(number_questions, 4);
            OnClickSwitch();
        } else {
            System.out.println("Введите что-нибудь");
        }
        ChangeStatePastButton();
        ChangeStateNextButton();
    }

    public void OnClickSwitch() throws FileNotFoundException {
        number_questions = number_questions + 1;
        SwitchTextQuestion();
        SwitchTextAnswer1();
        SwitchTextAnswer2();
        SwitchTextAnswer3();
        SwitchTextAnswer4();

        radio_button_1.setSelected(false);
        radio_button_2.setSelected(false);
        radio_button_3.setSelected(false);
        radio_button_4.setSelected(false);

        progress = progress + 0.1;
        SwitchProgressBar();
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

    public void SwitchProgressBar(){
        progressbar.setProgress(progress);
    }

    @FXML
    public void RadioButtonCheck(ActionEvent a) throws FileNotFoundException {
        RadioButton button = (RadioButton) a.getSource();

        switch (button.getId()){
            case("radio_button_1") -> ControllerQuestions.CheckAnswer(number_questions, 1);
            case("radio_button_2") -> ControllerQuestions.CheckAnswer(number_questions, 2);
            case("radio_button_3") -> ControllerQuestions.CheckAnswer(number_questions, 3);
            case("radio_button_4") -> ControllerQuestions.CheckAnswer(number_questions, 4);
            default -> System.out.println("Error");
        }
    }

    public static void ChangeStatePastButton(){
        if(number_questions>1){
            state_past_button = true;
        }
        else{
            state_past_button = false;
        }
    }

    public static void ChangeStateNextButton(){
        if(number_questions<10){
            state_next_button = true;
        }
        else{
            state_next_button = false;
        }
    }


    @FXML
    public void onClickExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
