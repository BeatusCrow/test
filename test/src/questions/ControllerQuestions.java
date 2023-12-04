package questions;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
 The class responsible for parsing the json file and changing the question/answer options
 */
public class ControllerQuestions {

    /*
     A thing for parsing json
     */
    public Questions parse(int number_questions) throws FileNotFoundException { // number_questions is responsible for the number of the current question in the application.
        Gson g = new Gson();
        FileReader reader = switch (number_questions) {
            case (1) -> new FileReader("q1.json");
            case (2) -> new FileReader("q2.json"); // If the current question is 2, then download the question and the answers to it from the q2 file.
            default -> new FileReader("q1.json");
        };

        return g.fromJson(reader, Questions.class);
    }

    // The method responsible for changing the question in the application. More precisely, for its textual part
    public static String TextQuestions(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getQuestion();
    }

    // The method responsible for changing the variant of the 1 answer to the question
    public static String TextAnswer1(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getAnswer_1();
    }

    // The method responsible for changing the variant of the 2 answer to the question
    public static String TextAnswer2(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getAnswer_2();
    }

    // The method responsible for changing the variant of the 3 answer to the question
    public static String TextAnswer3(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getAnswer_3();
    }

    // The method responsible for changing the variant of the 4 answer to the question
    public static String TextAnswer4(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getAnswer_4();
    }
}
