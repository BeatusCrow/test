package questions;



/*
 This is a class that creates an object in which our json will be stored. Well, or to deserialize a json object
 */
public class Questions {
    private Quest questions_1;
    private Quest questions_2;
    private Quest questions_3;

    public String getQuestions(int number){
        return switch (number) {
            case (1) -> questions_1.question;
            case (2) -> questions_2.question;
            case (3) -> questions_3.question;
            default -> "Error";
        };
    }

    public String getAnswer1(int number){
        return switch (number) {
            case (1) -> questions_1.answer1;
            case (2) -> questions_2.answer1;
            case (3) -> questions_3.answer1;
            default -> "Error";
        };
    }

    public String getAnswer2(int number){
        return switch (number) {
            case (1) -> questions_1.answer2;
            case (2) -> questions_2.answer2;
            case (3) -> questions_3.answer2;
            default -> "Error";
        };
    }

    public String getAnswer3(int number){
        return switch (number) {
            case (1) -> questions_1.answer3;
            case (2) -> questions_2.answer3;
            case (3) -> questions_3.answer3;
            default -> "Error";
        };
    }

    public String getAnswer4(int number){
        return switch (number) {
            case (1) -> questions_1.answer4;
            case (2) -> questions_2.answer4;
            case (3) -> questions_3.answer4;
            default -> "Error";
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
}

