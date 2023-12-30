package sample;

import db.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import questions.ControllerQuestions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 This is the most voluminous class in the program, responsible for many things:
 changing authorization windows, testing, results, changing the text of the question/ answers when switching between questions,
 pressing various buttons (save the answer, close the program, "✘")
 */


public class ControllerSwitch {

    // region @FXML

    /*
    I use fx:id, but it's better not to do that because of problems with the null value.
    It is better to use the lookup method for the scene, which finds the object by a simple id, rather than fx:.
     */
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

    // endregion


    // region Loading a new window

    /*
    These fields are needed to load a new window.
    After authorization, we load the test window, and after the test window, we load the results window
     */
    private Stage stage;
    private static Scene scene;
    private Parent root;
    private static int number_questions = 0;
    private static Text text_quest;

    private double xOffset = 0; // This is necessary for custom window dragging
    private double yOffset = 0;

    // endregion


    private static boolean state_past_button = false;
    private static boolean state_next_button = true;
    private static double progress;


    private static int first_time;

    private static String db_name;
    private static String db_soname;
    private static String db_job;
    private static String db_results;
    private static int db_time;
    private static int db_answer;


    /*
     This method checks whether the authorization fields are full and loads the test window accordingly.
     */
    @FXML
    public void onClickEntry(ActionEvent event) throws IOException {
        String name = this.name.getText().trim();
        String soname = this.soname.getText().trim();
        String job = this.job.getText().trim();

        // We check the authorization fields for fullness
        if (!Objects.equals(name, "") && !Objects.equals(soname, "") && !Objects.equals(job, "")) {
            db_name = name;
            db_soname = soname;
            db_job = job;

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/windows/TestWindow.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, Color.TRANSPARENT);
            stage.setScene(scene);
            first_time = (int) System.currentTimeMillis();
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
    public void onClickAnswer(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if(!ControllerQuestions.checkLastAnswer()){
            if(number_questions == 10){
                if(radio_button_1.isSelected()){
                    ControllerQuestions.setQuestionList(number_questions, 1);
                } else if(radio_button_2.isSelected()){
                    ControllerQuestions.setQuestionList(number_questions, 2);
                } else if(radio_button_3.isSelected()){
                    ControllerQuestions.setQuestionList(number_questions, 3);
                } else if(radio_button_4.isSelected()){
                    ControllerQuestions.setQuestionList(number_questions, 4);
                }
                number_questions = ControllerQuestions.getNumberAnswer();
                OnClickSwitch();
                SwitchStateRB(number_questions);
                ChangeStatePastButton();
                ChangeStateNextButton();
            }
            else{
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
                SwitchStateRB(number_questions);
                ChangeStatePastButton();
                ChangeStateNextButton();
            }
        }else{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/windows/ResultsWindow.fxml")));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root, Color.TRANSPARENT);
            stage.setScene(scene);
            SwitchStatistic();
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
    }

    public void SwitchStatistic() throws SQLException, ClassNotFoundException {
        String answers = Integer.toString(ControllerQuestions.getTrue_answer());
        Text text_number_true_answer = (Text) scene.lookup("#text_number_true_answer");
        text_number_true_answer.setText(answers);
        db_answer = ControllerQuestions.getTrue_answer();

        int second_time = (int) System.currentTimeMillis();
        int time = (second_time - first_time)/60000;
        Text text_number_minutes = (Text) scene.lookup("#text_number_minutes");
        text_number_minutes.setText(String.valueOf(time) + " минут");
        db_time = time;

        Text text_results = (Text) scene.lookup("#text_results");
        if(ControllerQuestions.getTrue_answer() > 7 && time < 15){
            text_results.setText("Зачет");
            db_results = "Зачет";
        }
        else{
            text_results.setText("Незачет");
            db_results = "Незачет";
        }

        dataUser();
    }

    private void dataUser() throws SQLException, ClassNotFoundException {
        DataBaseHandler.signUp(db_name, db_soname, db_job, db_results, db_time, db_answer);
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
