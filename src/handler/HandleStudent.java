package handler;

import datamodel.Quiz;
import datamodel.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mashayekhi on 18/02/2019.
 */
public class HandleStudent {


    public void loginStudent() throws ClassNotFoundException {

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
}
