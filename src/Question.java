import java.util.HashMap;

// Defines Object called "Question"
public class Question {
    private String questionText;
    private HashMap<String, HashMap<Character, Double>> answerValues;

    public Question(String questionText, HashMap<String, HashMap<Character, Double>> answerValues) {
        this.questionText = questionText;
        this.answerValues = answerValues;
    }

    public String getQuestionText() {
        return questionText;
    }

    public HashMap<String, HashMap<Character, Double>> getAnswerValues() {
        return answerValues;
    }

}

