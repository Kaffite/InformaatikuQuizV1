import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionBank {
    ArrayList<Question> questionList = new ArrayList<>();
    HashMap<Character, Double> results = new HashMap<>();

    public QuestionBank(String filename) throws IOException {
        this.questionList = ReadFromFile(filename);
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public HashMap<Character, Double> getResults() {
        return results;
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
            results.putIfAbsent(key, 0.0); // Adds the Character also to results HashMap
        }
        answerValues.put(answer, temporary);
    }

    public Question RandomQuestion(){
        int randomNumber = (int) (Math.random() * (questionList.size()));
        Question question = questionList.remove(randomNumber);
        return question;
    }

    public HashMap QuestionAnswerOptions(Question question){
        HashMap answerOptions = question.getAnswerValues();
        int i = 0;
        HashMap<Integer, String> questionAnswer = new HashMap<>();
        for (Object key : answerOptions.keySet()) {
            i++;
            System.out.println(i + ") " +  key);
            questionAnswer.put(i, key.toString());
        }
        return questionAnswer;
    }


}
