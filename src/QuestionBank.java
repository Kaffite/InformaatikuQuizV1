import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class QuestionBank {
    //QuestionList in here or move to the   readFromFile method???
    ArrayList<Question> questionList = new ArrayList<>();

    public QuestionBank(String filename) throws IOException {
        this.questionList = ReadFromFile(filename);
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    private ArrayList<Question> ReadFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String questionText = "";
            String line;
            HashMap<String, HashMap<Character, Double>> answerValues = new HashMap<>();
            while (true){
                line = br.readLine();
                if (line == null){
                    questionList.add(new Question(questionText, answerValues));
                    break;
                } else if (line.contains(";")) {// line = Answer option
                    AddAnswer(answerValues, line); // Adds answer to HashMap
                } else { //  line = Question text
                    if (questionText.equals("")) questionText = line;
                    else { // next question
                        questionList.add(new Question(questionText, answerValues));
                        answerValues = new HashMap<>(); // answerValues points to new HashMap so the Map in "questionList" wont change
                        questionText = line;
                    }
                }
            }
        }
        return questionList;
    }

    private void AddAnswer(HashMap<String, HashMap<Character, Double>> answerValues, String line) {
        HashMap<Character, Double> temporary = new HashMap<>();
        String [] components = line.split(";");
        String answer = components[0];
        for (int i = 1; i < components.length; i++) { //creating the Hashmap for that answer option
            Character key = (Character) components[i].split(" ")[0].charAt(0); // Type char
            Double value = (Double) Double.parseDouble(components[i].split(" ")[1]); // value for that type
            temporary.put(key, value);
        }
        answerValues.put(answer, temporary);
    }

    public void AskQuestion(){
        int randomNumber = (int) (Math.random() * (questionList.size()));
        Question question = questionList.remove(randomNumber);
        System.out.println(question.getQuestionText());
        HashMap answerOptions = question.getAnswerValues();
        int i = 0;
        for (Object key : answerOptions.keySet()) {
            i++;
            System.out.println(i + ") " +  key);
        }
    }


}
