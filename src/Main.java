import java.io.IOException;

public class Main {
    // Remove throws exception
    public static void main(String[] args) throws IOException {
        QuestionBank bank = new QuestionBank("QuizQuestions.txt");
        //catch FilenotFoundException -> ask for a name again
        System.out.println(bank.toString());
    }
}