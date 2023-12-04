package questions;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ControllerQuestions {

    public Questions parse(int number_questions) throws FileNotFoundException {
        Gson g = new Gson();
        FileReader reader = switch (number_questions) {
            case (1) -> new FileReader("q1.json");
            case (2) -> new FileReader("q2.json");
            default -> new FileReader("q1.json");
        };

        return g.fromJson(reader, Questions.class);
    }

    public static String TextQuestions(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getQuestion();
    }

    public static String TextAnswer1(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getAnswer_1();
    }

    public static String TextAnswer2(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getAnswer_2();
    }

    public static String TextAnswer3(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getAnswer_3();
    }

    public static String TextAnswer4(int number_questions) throws FileNotFoundException {

        ControllerQuestions q1 = new ControllerQuestions();
        Questions person = q1.parse(number_questions);

        return person.getAnswer_4();
    }
}
