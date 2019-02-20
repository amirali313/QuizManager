package datamodel;

import services.QuestionDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mashayekhi on 13/02/2019.
 */
public class Quiz {

    private Student student;

    private List<String> topics = new ArrayList<String>();
    private int difficulty;

    private List<MCQAnswer> mcqAnswers;
    private List<Answer> answers;
    private List<MCQQuestions> mcqQuestions;
    private List<Question> chosenQuestions;


    public Quiz(List<String> topics, int difficulty, Student student) {
        this.topics = topics;
        this.difficulty = difficulty;
        this.student = student;
    }

    public void takeQuiz() throws ClassNotFoundException {

        QuestionDAO questionJDBCDAO = new QuestionDAO();
        chosenQuestions = questionJDBCDAO.getQuestions(topics, difficulty);

        List<String> answers = new ArrayList<>();
        List<MCQAnswer> mcqAnswers = new ArrayList<>();
        int grade = 0;
        student.setQuestions(chosenQuestions);
        //We have got a list of questions based on difficulty and topics
        //now putting it in to for to let the student to take it.
        for (int i = 0; i < chosenQuestions.size(); i++) {
            int j = i + 1;

            System.out.println("-|-|-|-|-|-|-|-|-|-|-|-|-|-|-");
            System.out.println("id : " + chosenQuestions.get(i).getId());
            System.out.println("difficulty : " + chosenQuestions.get(i).getDifficulty());
            System.out.println("topics : " + chosenQuestions.get(i).getTopics());
            System.out.println("Q" + j + " : " + chosenQuestions.get(i).getQuestion());

            //meaning that it is an open question
            if (chosenQuestions.get(i).getValidChoice() == 0){
                System.out.println("-------------------");
                System.out.println("Enter your answer : ");
                Scanner scanner = new Scanner(System.in);
                String studentAnswer = scanner.nextLine();
                
                answers.add(studentAnswer);
                student.setAnswer(answers);

            }
            //this is when it is an MCQ Question
            else {
                for (int k = 0 ; k < chosenQuestions.get(i).getMcqAnswers().size() ; k ++) {
                    int c = k + 1;
                    System.out.println(c + " : " + chosenQuestions.get(i).getMcqAnswers().get(k));
                }
                System.out.println("-------------------");
                System.out.println("Enter your answer : (1 ,2 ,3 ,4)");
                Scanner scanner = new Scanner(System.in);
                int studentMCQAnswer = scanner.nextInt();
                MCQAnswer mcqAnswer = new MCQAnswer();
                mcqAnswer.setMcqAnswer(studentMCQAnswer);
                mcqAnswers.add(mcqAnswer);
                student.setMcqAnswer(mcqAnswers);
                if(studentMCQAnswer == chosenQuestions.get(i).getValidChoice())
                    grade = grade + 1;

            }
        }

        //grading to student
        if (chosenQuestions.size() != 0) {
            student.setGrade(grade);
            System.out.println("Your Grade : " + grade + "/" + student.getMcqAnswer().size());
        }

        exportQuiz(student);
    }

    private void exportQuiz(Student student) {
        try {
            PrintWriter writer = new PrintWriter("Results.txt", "UTF-8");
            writer.println("---------Results---------");
            writer.println("-------------------------");
            writer.println("");
            writer.println("Student name : " + student.getName());
            writer.println("");
            int k = 0;
            for (int i = 0 ; i < student.getQuestions().size() ; i++){

                writer.println("question #" + (i+1) + " : " + student.getQuestions().get(i).getQuestion());
                if (student.getQuestions().get(i).getValidChoice() != 0){
                    for (int j = 0 ; j < student.getQuestions().get(i).getMcqAnswers().size() ; j++) {
                        writer.println((j+1) + " : " + student.getQuestions().get(i).getMcqAnswers().get(j));
                    }
                    writer.println("correct Answer : " + student.getQuestions().get(i).getValidChoice());
                    writer.println("");
                }
                else {

                    writer.println("Your Answer : " + student.getAnswer().get(k));
                    writer.println("");
                    k++;
                }

            }


            writer.println("your grade : " + student.getGrade() + " / " + student.getMcqAnswer().size() + " MCQ Questions.");

            writer.close();
        } catch (IOException e) {
            System.out.println("could not write to file");
        }


    }



}
