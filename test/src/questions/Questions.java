package questions;

/*
 This is a class that creates an object in which our json will be stored. Well, or to deserialize a json object
 */
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


    /*
     In fact, it is not used anywhere, but if you suddenly need this thing during the debag process... Then she's ready)
     */
    @Override
    public String toString(){
        return question + answer_1 + answer_2 + answer_3 + answer_4;
    }
}
