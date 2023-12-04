package questions;

public class Questions {
    private String question;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;

    public String getQuestion() { return question; }

    public String getAnswer_1() { return answer_1; }

    public String getAnswer_2() { return answer_2; }

    public String getAnswer_3() { return answer_3; }

    public String getAnswer_4() { return answer_4; }


    @Override
    public String toString(){
        return question + answer_1 + answer_2 + answer_3 + answer_4;
    }
}
