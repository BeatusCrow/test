package questions;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
 The class responsible for parsing the json file and changing the question/answer options
 */
public class ControllerQuestions {

    // The method responsible for changing the question in the application. More precisely, for its textual part
    public static String TextQuestions(int number_questions) throws FileNotFoundException {
        Gson g = new Gson();
        FileReader reader = new FileReader("q1.json");
        Questions questions = g.fromJson(reader, Questions.class);

        System.out.println(questions.toString());
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
}
