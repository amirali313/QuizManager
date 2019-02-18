package handler;

import datamodel.MCQAnswer;
import datamodel.Question;
import services.MCQAnswerDAO;
import services.QuestionJDBCDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mashayekhi on 18/02/2019.
 */
public class HandleTeacher {

    public void loginTeacher() {
        System.out.println("-- Teacher Login --");
        System.out.println("Please enter your name and id : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String id = scanner.nextLine();

        if (authenticateTeacher(name, id)) {
            System.out.println("Welcome Professor " + name);
            handleTeacherRequest();

        }else {
            System.out.println("not authenticated, exiting");
        }

    }

    private static boolean authenticateTeacher(String name, String id) {
        File file = new File("Teachers.txt");
        Scanner scanner;
        String user = null;
        String pwd = null;
        try {
            scanner = new Scanner(file);
            String nextLine = scanner.nextLine();
            String[] parts = nextLine.split("=");
            user  = parts[0];
            pwd = parts[1];
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return (name.equals(user) && id.equals(pwd));
    }

    public static void handleTeacherRequest() {
        System.out.println("What can I do for you ?");
        System.out.println("1. Create a Question");
        System.out.println("2. Delete a Question");
        System.out.println("3. Update a Question");
        System.out.println("4. Show all Questions");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Question question = new Question();
                System.out.println("Type in your Question :");
                Scanner scanner1 = new Scanner(System.in);
                question.setQuestion(scanner1.nextLine());

                System.out.println("Type in the difficulty of your Question :");
                question.setDifficulty(scanner1.nextInt());

                System.out.println("Type in the topics of your Question : (2 topics max)");
                List<String> topics = new ArrayList<>();
                System.out.println("topic #1 : ");
                Scanner scanner2 = new Scanner(System.in);
                topics.add(scanner2.nextLine());
                System.out.println("topic #2 : (type n if there is only one topic) " );
                if (scanner2.nextLine() == "n")
                    question.setTopics(topics);
                else {
                    topics.add(scanner2.nextLine());
                    question.setTopics(topics);
                }

                QuestionJDBCDAO createQn = new QuestionJDBCDAO();
                createQn.create(question);

                System.out.println("Is the question and MCQ ? (y/n)");
                Scanner scanner7 = new Scanner(System.in);
                String yorn = scanner7.nextLine();
                if ("y".equals(yorn)){
                    System.out.println("Enter your MCQ Answers : (4 answers)");
                    Scanner scanner8 = new Scanner(System.in);
                    List<String> mcqAnswerList = new ArrayList<>();
                    for (int i=0 ; i < 4 ; i++){
                        mcqAnswerList.add(scanner8.nextLine());
                    }
                    question.setMcqAnswers(mcqAnswerList);

                    System.out.println("What is correct answers : (1, 2 , 3 , 4) ");
                    int validChoice = scanner8.nextInt();
                    question.setValidChoice(validChoice);

                    MCQAnswerDAO createAnswer = new MCQAnswerDAO();
                    createAnswer.create(question);
                }

                break;


            case 2:
                System.out.println("What question do you want to delete ?");
                System.out.println("-- Showing all the questions --");
                System.out.println("-------------------------------");
                QuestionJDBCDAO deleteQn = new QuestionJDBCDAO();
                List<Question> allQuestions;
                allQuestions = deleteQn.showAllQuestions();
                for (int i = 0; i < allQuestions.size(); i++) {
                    int j = i + 1;
                    System.out.println("id : " + allQuestions.get(i).getId());
                    System.out.println("difficulty : " + allQuestions.get(i).getDifficulty());
                    System.out.println("topics : " + allQuestions.get(i).getTopics());
                    System.out.println("Q" + j + " : " + allQuestions.get(i).getQuestion());
                }

                System.out.println("-------------------------------");
                System.out.println("ID of the Question :");
                Scanner scanner3 = new Scanner(System.in);
                int chosenId = scanner3.nextInt();
                Question question1 = new Question();
                question1.setId(chosenId);

                deleteQn.delete(question1);

                break;

            case 3:
                System.out.println("What question title do you want to Update ?");
                System.out.println("-- Showing all the questions --");
                System.out.println("-------------------------------");
                QuestionJDBCDAO updateQuestion = new QuestionJDBCDAO();
//                List<Question> allQuestions;
                allQuestions = updateQuestion.showAllQuestions();
                for (int i = 0; i < allQuestions.size(); i++) {
                    int j = i + 1;
                    System.out.println("id : " + allQuestions.get(i).getId());
                    System.out.println("difficulty : " + allQuestions.get(i).getDifficulty());
                    System.out.println("topics : " + allQuestions.get(i).getTopics());
                    System.out.println("Q" + j + " : " + allQuestions.get(i).getQuestion());
                }

                System.out.println("-------------------------------");
                System.out.println("ID of the Question :");
                Scanner scanner4 = new Scanner(System.in);
                chosenId = scanner4.nextInt();
                System.out.println("Enter your new question title :");
                Scanner scanner5 = new Scanner(System.in);
                String title = scanner5.nextLine();
                System.out.println("Enter new difficulty : (1 , 2 , 3)");
                int difficulty = scanner5.nextInt();
                Question updateQ = new Question();
                updateQ.setId(chosenId);
                updateQ.setDifficulty(difficulty);
                updateQ.setQuestion(title);
                updateQuestion.update(updateQ);

                break;

            case 4:
                System.out.println("-- Showing all the questions --");
                System.out.println("-------------------------------");
                QuestionJDBCDAO showAllQuestions = new QuestionJDBCDAO();
//                List<Question> allQuestions;
                allQuestions = showAllQuestions.showAllQuestions();
                for (int i = 0; i < allQuestions.size(); i++) {
                    int j = i + 1;
                    System.out.println("id : " + allQuestions.get(i).getId());
                    System.out.println("difficulty : " + allQuestions.get(i).getDifficulty());
                    System.out.println("topics : " + allQuestions.get(i).getTopics());
                    System.out.println("Q" + j + " : " + allQuestions.get(i).getQuestion());
                    System.out.println("Choices : " + allQuestions.get(i).getMcqAnswers());
                    System.out.println("Correct Answer number : " + allQuestions.get(i).getValidChoice());
                }

                break;

            default:
                System.out.println("You have entered a wrong input !");
        }


    }

}
