package com.launcher;

import handler.HandleStudent;
import handler.HandleTeacher;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        while (true) {
            /** Main Menu **/
            System.out.println("Hello User!");
            System.out.println("----------");
            System.out.println("1.Teacher");
            System.out.println("2.Student");
            System.out.println("0.EXIT");
            System.out.println("----------");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                /** Teachers requests are handled in HandleTeacher Class **/
                HandleTeacher handleTeacher = new HandleTeacher();
                handleTeacher.loginTeacher();
            } else if (choice == 2) {
                /** Student quiz is handled in HandleStudent Class **/
                HandleStudent handleStudent = new HandleStudent();
                handleStudent.loginStudent();
            } else if (choice == 0) {
                return;
            } else
                System.out.println("Entered wrong argument !");

        }
    }
}
