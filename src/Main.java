import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Descriptions descriptions = new Descriptions("Descriptions.txt");
        QuestionBank bank = bank = new QuestionBank("Questions.txt");
        Points points = new Points(bank.getPointMap());
        askQuestions(sc, bank, points);
        displayResults(points, descriptions);
    }

    /**
     * Function:
     * 1) Displays results for each student type
     * 2) Prints Descriptions for the type(s) that got the most points
     * @param points Stores how many points each type has
     * @param descriptions Stores all descriptions
     */
    private static void displayResults(Points points, Descriptions descriptions) {
        // Displays points for each student type
        HashMap pointMap = points.getPoints();
        HashMap charTypeMap = descriptions.getTypes();
        System.out.println("\nSinu tulemused: ");
        for (Object o : pointMap.keySet()) {
            String type = (String) charTypeMap.get(o);
            Double typePoints = (Double) pointMap.get(o);
            System.out.println(type + " - " + typePoints + " punkti");
        }
        // Displays the descriptions of the student type(s) that the user got the most points for
        HashMap descMap = descriptions.getDescriptions();
        List<Map.Entry<Character, Double>> list = new ArrayList<>(pointMap.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        double max = list.get(0).getValue();
        for (Map.Entry<Character, Double> entry : list) {
            // Stops loop if all the types that got max points have been printed
            if (entry.getValue() != max) break;
            Character typeChar = entry.getKey();
            String description = (String) descMap.get(typeChar);
            String type = (String) charTypeMap.get(typeChar);
            String[] descParts = description.split("\\. ");
            System.out.println("\nSa oled " + type + ":");
            for (String part : descParts) {
                System.out.println(part);
            }
            System.out.println("");
        }
    }

    /**
     * Function:
     * 1) Asks questions from questionlist
     * 2) Based on what user answered, adds points to student types
     * 3) Removes asked question.
     * 4) Stops asking questions if the questionlist is empty.
     * @param sc Scanner for reading user input
     * @param bank Used for asking questions and displaying all the possible answers
     * @param points Adds points to corresponding type based on user input
     * @throws InterruptedException
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
                    int userAnswer =  Integer.parseInt(sc.nextLine());
                    String chosenOption = answerOptions.get(userAnswer).toString();
                    HashMap optionPoints = question.getAnswerValues().get(chosenOption);
                    points.addPoints(optionPoints);
                    System.out.println("");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\nVastuseks sobib ainult number. Proovi uuesti.");
                    Thread.sleep(3000);
                } catch (NullPointerException e) {
                    System.out.println
                            ("\nSisestatud number peab vastama mingile " +
                            "vastusevariandis etteantud numbrile." +
                            " \nProovi uuesti. ");
                    Thread.sleep(4000);
                }
                System.out.println(""); // An empty line after Exception (readability)
            }
        }
    }
}