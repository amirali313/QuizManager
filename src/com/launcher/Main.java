package com.launcher;

import handler.HandleStudent;
import handler.HandleTeacher;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("Hello User!");
        System.out.println("1.Teacher");
        System.out.println("2.Student");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            HandleTeacher handleTeacher = new HandleTeacher();
            handleTeacher.loginTeacher();
        }

        else if (choice == 2){

            HandleStudent handleStudent = new HandleStudent();
            handleStudent.loginStudent();
        }
        else
            System.out.println("Entered wrong argument !");

    }
}
