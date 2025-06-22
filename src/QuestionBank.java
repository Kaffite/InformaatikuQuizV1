import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionBank {
    //QuestionList in here or move to the   readFromFile method???
    ArrayList<Question> questionList;

    public QuestionBank(String filename) {
        this.questionList = readFromFile(filename);
    }

    private ArrayList<Question> readFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            String questionText = "";
            HashMap<String, HashMap<Character, Float>> answerValues = new HashMap<>();
            while (!line.isEmpty()) {
                if (line.contains(";")) {// line = Answer option
                    AddAnswer(answerValues, line); // Adds answer to HashMap
                } else { //  line = Question text
                    if (questionText.equals("")) questionText = line;
                    else { // next question
                        questionList.add(new Question(questionText, answerValues));
                        questionText = line;
                    }
                }
            }
        }
        return questionList;
    }

    private void AddAnswer(HashMap<String, HashMap<Character, Float>> answerValues, String line) {
        HashMap<Character, Float> temporary = new HashMap<>();
        String [] components = line.split(";");
        String answer = components[0];
        for (int i = 1; i < components.length; i++) { //creating the Hashmap for that answer option
            Character key = components[i].split(" ")[0].charAt(0); // Type char
            Float value = Float.parseFloat(components[i].split(" ")[1]); // value for that type
            temporary.put(key, value);
        }
        answerValues.put(answer, temporary);
    }
}
