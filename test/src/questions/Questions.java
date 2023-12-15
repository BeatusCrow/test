package questions;



/*
 This is a class that creates an object in which our json will be stored. Well, or to deserialize a json object
 */
public class Questions {
    private Quest questions_1;
    private Quest questions_2;
    private Quest questions_3;
    private Quest questions_4;
    private Quest questions_5;
    private Quest questions_6;
    private Quest questions_7;
    private Quest questions_8;
    private Quest questions_9;
    private Quest questions_10;


    public String getQuestions(int number){
        return switch (number) {
            case (1) -> questions_1.question;
            case (2) -> questions_2.question;
            case (3) -> questions_3.question;
            case (4) -> questions_4.question;
            case (5) -> questions_5.question;
            case (6) -> questions_6.question;
            case (7) -> questions_7.question;
            case (8) -> questions_8.question;
            case (9) -> questions_9.question;
            case (10) -> questions_10.question;
            default -> "Error";
        };
    }

    public String getAnswer1(int number){
        return switch (number) {
            case (1) -> questions_1.answer1;
            case (2) -> questions_2.answer1;
            case (3) -> questions_3.answer1;
            case (4) -> questions_4.answer1;
            case (5) -> questions_5.answer1;
            case (6) -> questions_6.answer1;
            case (7) -> questions_7.answer1;
            case (8) -> questions_8.answer1;
            case (9) -> questions_9.answer1;
            case (10) -> questions_10.answer1;
            default -> "Error";
        };
    }

    public String getAnswer2(int number){
        return switch (number) {
            case (1) -> questions_1.answer2;
            case (2) -> questions_2.answer2;
            case (3) -> questions_3.answer2;
            case (4) -> questions_4.answer2;
            case (5) -> questions_5.answer2;
            case (6) -> questions_6.answer2;
            case (7) -> questions_7.answer2;
            case (8) -> questions_8.answer2;
            case (9) -> questions_9.answer2;
            case (10) -> questions_10.answer2;
            default -> "Error";
        };
    }

    public String getAnswer3(int number){
        return switch (number) {
            case (1) -> questions_1.answer3;
            case (2) -> questions_2.answer3;
            case (3) -> questions_3.answer3;
            case (4) -> questions_4.answer3;
            case (5) -> questions_5.answer3;
            case (6) -> questions_6.answer3;
            case (7) -> questions_7.answer3;
            case (8) -> questions_8.answer3;
            case (9) -> questions_9.answer3;
            case (10) -> questions_10.answer3;
            default -> "Error";
        };
    }

    public String getAnswer4(int number){
        return switch (number) {
            case (1) -> questions_1.answer4;
            case (2) -> questions_2.answer4;
            case (3) -> questions_3.answer4;
            case (4) -> questions_4.answer4;
            case (5) -> questions_5.answer4;
            case (6) -> questions_6.answer4;
            case (7) -> questions_7.answer4;
            case (8) -> questions_8.answer4;
            case (9) -> questions_9.answer4;
            case (10) -> questions_10.answer4;
            default -> "Error";
        };
    }

    public int getTrueAnswer(int number){
        return switch (number) {
            case (1) -> questions_1.true_answer;
            case (2) -> questions_2.true_answer;
            case (3) -> questions_3.true_answer;
            case (4) -> questions_4.true_answer;
            case (5) -> questions_5.true_answer;
            case (6) -> questions_6.true_answer;
            case (7) -> questions_7.true_answer;
            case (8) -> questions_8.true_answer;
            case (9) -> questions_9.true_answer;
            case (10) -> questions_10.true_answer;
            default -> 1;
        };
    }

    @Override
    public String toString(){
        return questions_1.question + questions_1.answer1 + questions_1.answer2 + questions_1.answer3 + questions_1.answer4 +  questions_2.question + questions_2.answer1 + questions_2.answer2 + questions_2.answer3 + questions_2.answer4;
    }
}

class Quest {
    public String question;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public int true_answer;
}

