package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
    Purpose:
    1) Read questions from a file to an Arraylist
    2) Create an empty Hashmap for points (pointMap) that Backend.Points uses as a template
    3) Ask questions in a random order and display the answer options and corresponding numbers
*/
public class QuestionBank {
    ArrayList<Question> questionList = new ArrayList<>();
    HashMap<Character, Double> pointMap = new HashMap<>();

    public QuestionBank(String filename) throws IOException {
        this.questionList = readFromFile(filename);
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public HashMap<Character, Double> getPointMap() {
        return pointMap;
    }

    /**
     * Function: Reads Questions (and possible answers to the questions) from a .txt file
     * @param filename the name of the .txt file
     * @return Arraylist that contains Questions (Backend.Question format defined in class 'Backend.Question')
     * @throws IOException
     */
    private ArrayList<Question> readFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String questionText = "";
            String line;
            // String = optionText, Hashmap = how many points for what type
            HashMap<String, HashMap<Character, Double>> answerValues = new HashMap<>();
            while (true){
                line = br.readLine();
                if (line.equals("---")) {
                    continue;
                }else if (line.equals("")){
                    questionList.add(new Question(questionText, answerValues));
                    break;
                } else if (line.contains(";")) {// line = question option
                    addAnswer(answerValues, line); // Adds answer to HashMap

                } else { //  line = Backend.Question text
                    if (questionText.equals("")) questionText = line;
                    else { // next question
                        questionList.add(new Question(questionText, answerValues));
                        answerValues = new HashMap<>(); // clears Hashmap for next question
                        questionText = line;
                    }
                }
            }
        }
        return questionList;
    }

    /**
     * Function: Support class for reading from file.
     * Creates a hashmap for all the possible answers for a question
     * @param answerValues (Hashmap: type, pointAmount) if that answer is chosen - different for each answer to a question
     * @param line line read from the .txt file
     */
    private void addAnswer(HashMap<String, HashMap<Character, Double>> answerValues, String line) {
        HashMap<Character, Double> temporary = new HashMap<>();
        String [] components = line.split(";");
        String answer = components[0];
        for (int i = 1; i < components.length; i++) { //creating the Hashmap for that answer option
            String current = components[i].substring(1);
            Character key = (Character) current.split(" ")[0].charAt(0); // Type char
            Double value = (Double) Double.parseDouble(current.split(" ")[1]); // value for that type
            temporary.put(key, value);
            pointMap.putIfAbsent(key, 0.0); // Adds the Character also to results HashMap
        }
        answerValues.put(answer, temporary);
    }

    /**
     *  Function:
     *  1) Chooses a random question from the questionlist.
     *  2) Removes the question that was asked from the questionlist.
     * @return Returns the question that was chosen (removed)
     */
    public Question randomQuestion(){
        int randomNumber = (int) (Math.random() * (questionList.size()));
        Question question = questionList.remove(randomNumber);
        return question;
    }

    /**
     *  Function:
     *  1) Matches each choice option with a number
     *  2) prints the text of question option and corresponding number
     *  3) returns a hashmap of option choices:
     *       key   = number (of the option)
     *       value = text (of the option)
     * @param question the question that is being asked at the moment
     * @return Hashmap with all the choice options
     */
    public HashMap answers(Question question){
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
