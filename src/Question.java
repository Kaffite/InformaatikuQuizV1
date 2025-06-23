import java.util.HashMap;

public class Question {
    private String questionText;
    private HashMap<String, HashMap<Character, Double>> answerValues;

    public Question(String questionText, HashMap<String, HashMap<Character, Double>> answerValues) {
        this.questionText = questionText;
        this.answerValues = answerValues;
    }

    @Override
    public String toString() {
        return questionText + " " + answerValues;
    }
}

