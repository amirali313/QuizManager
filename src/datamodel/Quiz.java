package datamodel;

import services.QuestionJDBCDAO;

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

        QuestionJDBCDAO questionJDBCDAO = new QuestionJDBCDAO();
        chosenQuestions = questionJDBCDAO.getQuestions(topics, difficulty);
        List<Answer> answers = new ArrayList<>();
        List<MCQAnswer> mcqAnswers = new ArrayList<>();
        int grade = 0;
        student.setQuestions(chosenQuestions);

        for (int i = 0; i < chosenQuestions.size(); i++) {
            int j = i + 1;

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
                Answer answer = new Answer();
                answer.setText(studentAnswer);
                answers.add(answer);
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

        if (chosenQuestions.size() != 0) {
            student.setGrade(grade);
            System.out.println("Your Grade : " + grade + "/" + student.getMcqAnswer().size());
        }
    }



}
