package Backend;

import java.util.HashMap;

/*
    Purpose: Defines The structure of a question.
    Variables:
    1) questionText - The text of the question
    2) answerValues - Map for option (What option gives how many points to what student type)
 */
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

