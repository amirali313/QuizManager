package com.launcher;

import datamodel.Quiz;
import datamodel.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        System.out.println("please enter your name and id :");
        Scanner scanner = new Scanner(System.in);
        handleStudentQuiz(scanner);



//        Student student = new Student(topics,difficulty);


    }

    public static void handleStudentQuiz(Scanner scanner){
        String name = scanner.nextLine();
        String id = scanner.nextLine();

        Student student = new Student(name,id);

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
