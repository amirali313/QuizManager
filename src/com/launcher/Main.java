package com.launcher;

import datamodel.Question;
import datamodel.Quiz;
import datamodel.Student;
import services.QuestionJDBCDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("Hello User!");
        System.out.println("1.Teacher");
        System.out.println("2.Student");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1)
            loginTeacher();

        else if (choice == 2)
            loginStudent();
        else
            System.out.println("Entered wrong argument !");

    }

    public static void loginTeacher() {
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

    public static void loginStudent() throws ClassNotFoundException {

        System.out.println("-- Student Login --");
        System.out.println("Please enter your name and id : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String id = scanner.nextLine();

        if (authenticateStudent(name, id)) {
            System.out.println("Welcome " + name);
            Student student = new Student(name,id);

            handleStudentQuiz(student);

        }else {
            System.out.println("not authenticated, exiting");
        }

    }

    private static boolean authenticateStudent(String name, String id) {
        File file = new File("Students.txt");
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

    public static void handleStudentQuiz(Student student) throws ClassNotFoundException {

        System.out.println("please enter your topics :");
        Scanner scanner1 = new Scanner(System.in);

        List<String> topics = new ArrayList<String>();

        for (int i = 0 ; i < 2 ; i++) {
            topics.add(scanner1.nextLine());
        }

        System.out.println("please enter level of difficulty :");
        System.out.println("(1 | 2 | 3 levels)");
        Scanner scanner2 = new Scanner(System.in);

        int difficulty = scanner2.nextInt();

        Quiz quiz = new Quiz(topics , difficulty);

        quiz.takeQuiz();

        //TODO store the quiz with the student information into database


    }

    public static void handleTeacherRequest() {
        System.out.println("What can I do for you ?");
        System.out.println("1. Create a Question");
        System.out.println("2. Delete a Question");
        System.out.println("3. Update a Question");
        System.out.println("4. Search Questions");

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

                break;


            case 2:
                System.out.println("What question do you want to delete ?");
                System.out.println("-- Showing all the questions --");
                System.out.println("-------------------------------");
                //TODO display all questions only with there id

                System.out.println("-------------------------------");
                System.out.println("ID of the Question :");
                Scanner scanner3 = new Scanner(System.in);
                int chosenId = scanner3.nextInt();
                //TODO DELETE THE QUESTION

                break;

            case 3:
                System.out.println("What question title do you want to Update ?");
                System.out.println("-- Showing all the questions --");
                System.out.println("-------------------------------");
                //TODO display all questions only with there id

                System.out.println("-------------------------------");
                System.out.println("ID of the Question :");
                Scanner scanner4 = new Scanner(System.in);
                chosenId = scanner4.nextInt();
                //TODO UPdate the question title

                break;

            case 4:
                System.out.println("What questions do you wish to see based on difficulty ?");
                System.out.println("Enter the Difficulty : ");
                Scanner scanner5 = new Scanner(System.in);
                int chosenDif = scanner5.nextInt();
                //TODO SHOW questions based on there difficulty




            default:
                System.out.println("You have entered a wrong input !");
        }


    }


}
