package datamodel;

import services.QuestionJDBCDAO;

import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < chosenQuestions.size(); i++) {
            int j = i + 1;

            System.out.println("id : " + chosenQuestions.get(i).getId());
            System.out.println("difficulty : " + chosenQuestions.get(i).getDifficulty());
            System.out.println("topics : " + chosenQuestions.get(i).getTopics());
            System.out.println("Q" + j + " : " + chosenQuestions.get(i).getQuestion());



            //TODO get answer from student scanner
        }

        //TODO export
    }



}
