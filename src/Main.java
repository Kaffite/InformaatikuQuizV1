import java.io.IOException;
import java.util.ArrayList;

public class Main {
    // Remove throws exception
    public static void main(String[] args) throws IOException {
        QuestionBank bank = new QuestionBank("QuizQuestions.txt");
        ArrayList<Question> questions = bank.getQuestionList();
        for (Question question : questions) {
            bank.AskQuestion();

        }
        //catch FilenotFoundException -> ask for a name again
    }
}