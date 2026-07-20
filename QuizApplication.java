import java.util.Scanner;
import java.util.concurrent.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApplication{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Question[] quiz = {
                new Question(
                        "1. What is the capital of India?",
                        new String[]{"1. Mumbai", "2. Delhi", "3. Hyderabad", "4. Chennai"},
                        2),

                new Question(
                        "2. Which language is used for Android development?",
                        new String[]{"1. Python", "2. Java", "3. C", "4. HTML"},
                        2),

                new Question(
                        "3. Who invented Java?",
                        new String[]{"1. Dennis Ritchie", "2.Bjarne Stroustrup ", "3.James Goslingo", "4. Guido van Rossum"},
                        3),

                new Question(
                        "4. Which keyword is used to inherit a class in Java?",
                        new String[]{"1. implements", "2.super ", "3. inherit", "4.  extends"},
                        4),

                new Question(
                        "5. Which company developed Java?",
                        new String[]{"1. Microsoft", "2. Sun Microsystems", "3. Apple", "4. IBM"},
                        2)
        };

        int score = 0;
        String[] summary = new String[quiz.length];

        System.out.println("===== QUIZ APPLICATION =====");
        System.out.println("You have 10 seconds for each question.\n");

        for (int i = 0; i < quiz.length; i++) {

            System.out.println(quiz[i].question);

            for (String option : quiz[i].options) {
                System.out.println(option);
            }

            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<Integer> future = executor.submit(() -> {
                System.out.print("Enter your answer (1-4): ");
                return sc.nextInt();
            });

            int answer = -1;

            try {
                answer = future.get(10, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.out.println("\nTime is up!");
            } catch (Exception e) {
                System.out.println("Invalid input.");
            } finally {
                executor.shutdownNow();
            }

            if (answer == quiz[i].correctAnswer) {
                score++;
                summary[i] = "Question " + (i + 1) + ": Correct";
            } else {
                summary[i] = "Question " + (i + 1) + ": Incorrect";
            }

            System.out.println();
        }

        System.out.println("========== RESULT ==========");
        System.out.println("Total Questions : " + quiz.length);
        System.out.println("Correct Answers : " + score);
        System.out.println("Wrong Answers   : " + (quiz.length - score));
        System.out.println("Score           : " + score + "/" + quiz.length);

        System.out.println("\nSummary:");
        for (String s : summary) {
            System.out.println(s);
        }

        sc.close();
    }
}