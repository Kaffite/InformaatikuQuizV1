import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        QuestionBank bank = readFromFile(sc);
        Points points = new Points(bank.getResults());
        System.out.println(points.toString());
        askQuestions(sc, bank, points);

    }

    private static void askQuestions(Scanner sc, QuestionBank bank, Points points) throws InterruptedException {
        while (true){
            try {
                ArrayList<Question> questions = bank.getQuestionList();
                while (questions.size() > 0) {
                    Question question = bank.RandomQuestion();
                    HashMap answerOptions = bank.QuestionAnswerOptions(question);
                    System.out.println(question.getQuestionText());
                    System.out.print("Sisesta vastus (number): ");
                    int userAnswer = Integer.parseInt(sc.nextLine());
                    String chosenOption = answerOptions.get(userAnswer).toString();
                    HashMap optionPoints = question.getAnswerValues().get(chosenOption);
                    points.AddPoints(optionPoints);
                }
                break;
                // Add Exception if the number is unfitting
            } catch (NumberFormatException e) {
                System.out.println("Vastuseks sobib ainult number. \nProovi uuesti \n");
                Thread.sleep(3000);
            }
        }
    }

    private static QuestionBank readFromFile(Scanner sc) throws InterruptedException, IOException{
        QuestionBank bank;
        while (true) {
            try {
                System.out.print("Sisesta faili nimi: ");
                String fileName = sc.nextLine();
                bank = new QuestionBank(fileName);
                break;
            } catch (FileNotFoundException e) { //ask for a name again
                System.out.println("Sellist faili ei leidu.\nKontrolli kas sisestasid faili nime korrektselt.\n");
                Thread.sleep(3000);
            }
        }
        return bank;
    }
}