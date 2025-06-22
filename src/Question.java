import java.util.HashMap;

public class Question {
    private String questionText;
    private HashMap<String, HashMap<Character, Float>> answerValues;

    public Question(String questionText, HashMap<String, HashMap<Character, Float>> answerValues) {
        this.questionText = questionText;
        this.answerValues = answerValues;
    }

}

