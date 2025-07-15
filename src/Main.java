import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Descriptions descriptions = new Descriptions("Descriptions.txt");
        QuestionBank bank = readFromFile(sc);
        Points points = new Points(bank.getPointMap());
        askQuestions(sc, bank, points);
        displayResults(points, descriptions);
    }

    private static void displayResults(Points points, Descriptions descriptions) {
        HashMap pointMap = points.getPoints();
        HashMap charTypeMap = descriptions.getTypes();
        System.out.println("\nSinu tulemused: ");
        double maxPoints = 0;
        ArrayList<Character> userTypes = new ArrayList<>();
        for (Object o : pointMap.keySet()) {
            Character oChar = (Character) o.toString().charAt(0);
            String type = (String) charTypeMap.get(oChar);
            Double typePoints = (Double) pointMap.get(oChar);
            System.out.println(type + "- " + typePoints + " punkti");
            if (typePoints > maxPoints) {
                maxPoints = typePoints;
                userTypes.clear();
                userTypes.add(oChar);
            }
            else if (typePoints == maxPoints){
                userTypes.add(oChar);
            }
        }
        // Displays what class user is

    }

    /*
        Function:
         Asks questions from questionlist
         Based on what user answered, adds points to student types
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
                    //System.out.print("Sisesta vastus (number): ");
                    int userAnswer = 2;//Integer.parseInt(sc.nextLine());
                    String chosenOption = answerOptions.get(userAnswer).toString();
                    HashMap optionPoints = question.getAnswerValues().get(chosenOption);
                    points.addPoints(optionPoints);
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
                //System.out.print("Sisesta faili nimi: ");
                String fileName = "Questions.txt"; //sc.nextLine();
                bank = new QuestionBank(fileName);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Sellist faili ei leidu.\nKontrolli kas sisestasid faili nime korrektselt.\n");
                Thread.sleep(3000);
            }
        }
        return bank;
    }
}