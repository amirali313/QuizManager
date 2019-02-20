package com.launcher;

import handler.HandleStudent;
import handler.HandleTeacher;
import services.Configuration;

import java.util.Scanner;

/**
 * Main menu.
 * This function will display a MENU in the console. Teacher or student option can be chosen, or one can exit this program.
 */


public class Main {


    public static void main(String[] args) throws ClassNotFoundException {
        Configuration.firstLoad();
        while (true) {


            System.out.println("Hello User!");
            System.out.println("----------");
            System.out.println("1.Teacher"); //user id in teacher.txt
            System.out.println("2.Student"); // user id in student.txt
            System.out.println("0.EXIT - (Click before Closing)"); //needs to be clicked to drop tables
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

                // 0 SHOULD BE CLICKED TO CLEAR DATABASE

                Configuration.dropLoad();
                return;
            } else
                System.out.println("Entered wrong argument !");

        }
    }
}
