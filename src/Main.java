import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
//        Siia laheb:
//          kysib kirjelduste faili nime
//          Kui error nimega, kysib uuesti
//          Kas IOException ka pyyda kinni -> teavitada, et formaat vale
        QuestionBank bank = readFromFile(sc);
        Points points = new Points(bank.getPointMap());
        askQuestions(sc, bank, points);
        System.out.println(points.getPoints());
    }

/*
    Function:
     Asks questions from questionlist
     Removes asked question.
     Stops asking questions if the questionlist is empty.
*/
    private static void askQuestions(Scanner sc, QuestionBank bank, Points points) throws InterruptedException {
        ArrayList<Question> questions = bank.getQuestionList();
        while (questions.size() > 0) {
            Question question = bank.randomQuestion();
            while (true) {
                try {
                    System.out.println(question.getQuestionText());
                    HashMap answerOptions = bank.answers(question);
                    System.out.print("Sisesta vastus (number): ");
                    int userAnswer = Integer.parseInt(sc.nextLine());
                    String chosenOption = answerOptions.get(userAnswer).toString();
                    HashMap optionPoints = question.getAnswerValues().get(chosenOption);
                    points.AddPoints(optionPoints);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Vastuseks sobib ainult number. \nProovi uuesti.");
                    Thread.sleep(3000);
                    System.out.println("");
                } catch (NullPointerException e) {
                    System.out.println("Sisestatud number peab vastama mingile " +
                            "vastusevariandis etteantud numbrile." +
                            " \nProovi uuesti. ");
                    Thread.sleep(3000);
                    System.out.println("");
                }
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