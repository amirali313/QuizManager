package com.launcher;

import datamodel.Quiz;
import datamodel.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello User!");
        System.out.println("Please login using your name and id : ");
        Scanner scanner = new Scanner(System.in);
        loginUser(scanner);

    }

    public static void loginUser(Scanner scanner){
        String name = scanner.nextLine();
        String id = scanner.nextLine();

        if (authenticate(name, id)) {
            System.out.println("Welcome " + name);
            Student student = new Student(name,id);

            handleStudentQuiz(student);

        }else {
            System.out.println("not authenticated, exiting");
        }

    }

    private static boolean authenticate(String name, String id) {
        File file = new File("auth.txt");
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

    public static void handleStudentQuiz(Student student){

        System.out.println("please enter your topics :");
        Scanner scanner1 = new Scanner(System.in);

        List<String> topics = new ArrayList<String>();

        while(scanner1.nextLine()!= null)
            topics.add(scanner1.nextLine());

        System.out.println("please enter level of difficulty :");
        System.out.println();
        Scanner scanner2 = new Scanner(System.in);

        int difficulty = scanner2.nextInt();

        Quiz quiz = new Quiz(topics , difficulty);

        quiz.takeQuiz();

        //TODO store the quiz with the student information into database


    }


}
