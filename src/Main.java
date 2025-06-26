import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Remove throws certain exceptions
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        QuestionBank bank;

        //Read from file
        while (true) {
            try {
                System.out.print("Sisesta faili nimi: ");
                String fileName = sc.nextLine();
                bank = new QuestionBank(fileName);
                break;
            } catch (FileNotFoundException e) { //ask for a name again
                System.out.println("Sellist faili ei leidu.\nKontrolli kas sisestasid faili nime korrektselt.\n");
                Thread.sleep(3000);
                continue;
            }
        }

        // Ask questions
        while (true){
            try {
                ArrayList<Question> questions = bank.getQuestionList();
                while (questions.size() > 0) {
                    bank.AskQuestion();
                    System.out.print("Sisesta vastus (number): ");
                    int answer = Integer.parseInt(sc.nextLine());
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println();
            }
        }

    }
}