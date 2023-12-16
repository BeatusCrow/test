package questions;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
 The class responsible for parsing the json file and changing the question/answer options
 */
public class ControllerQuestions {

    private static int true_answer = 0;
    private static final int[] questions_list = new int[11];

    public static void setQuestionList(int number_question, int answer){
        questions_list[number_question] = answer;
    }

    public static int getTrue_answer(){
        return true_answer;
    }

    public static boolean checkLastAnswer(){
        int count = 0;
        for(int i = 1; i < questions_list.length; i++){
            if(questions_list[i] == 0)
                count++;
        }
        switch (count){
            case (1) -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public static int getNumberAnswer(){
        System.out.println(Arrays.toString(questions_list));
        for(int i = 1; i < questions_list.length; i++){
            if(questions_list[i] == 0){
                return i-1;
            }
        }
        return 10;
    }

    public static int getQuestionList(int number_question){
        return questions_list[number_question];
    }

    // The method responsible for changing the question in the application. More precisely, for its textual part
    public static String TextQuestions(int number_questions) throws FileNotFoundException {
        Gson g = new Gson();
        FileReader reader = new FileReader("q1.json");
        Questions questions = g.fromJson(reader, Questions.class);

        return questions.getQuestions(number_questions);
    }

    // The method responsible for changing the variant of the 1 answer to the question
    public static String TextAnswer1(int number_questions) throws FileNotFoundException {
        Gson g = new Gson();
        FileReader reader = new FileReader("q1.json");
        Questions questions = g.fromJson(reader, Questions.class);


        return questions.getAnswer1(number_questions);
    }

    // The method responsible for changing the variant of the 2 answer to the question
    public static String TextAnswer2(int number_questions) throws FileNotFoundException {
        Gson g = new Gson();
        FileReader reader = new FileReader("q1.json");
        Questions questions = g.fromJson(reader, Questions.class);

        return questions.getAnswer2(number_questions);
    }

    // The method responsible for changing the variant of the 3 answer to the question
    public static String TextAnswer3(int number_questions) throws FileNotFoundException {
        Gson g = new Gson();
        FileReader reader = new FileReader("q1.json");
        Questions questions = g.fromJson(reader, Questions.class);

        return questions.getAnswer3(number_questions);
    }

    // The method responsible for changing the variant of the 4 answer to the question
    public static String TextAnswer4(int number_questions) throws FileNotFoundException {
        Gson g = new Gson();
        FileReader reader = new FileReader("q1.json");
        Questions questions = g.fromJson(reader, Questions.class);

        return questions.getAnswer4(number_questions);
    }

    public static void CheckAnswer(int number_questions, int number_radio_button) throws FileNotFoundException {
        Gson g = new Gson();
        FileReader reader = new FileReader("q1.json");
        Questions questions = g.fromJson(reader, Questions.class);

        if(number_radio_button == questions.getTrueAnswer(number_questions)){
            true_answer++;
        }
    }
}
