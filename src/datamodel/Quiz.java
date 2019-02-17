package datamodel;

import services.QuestionJDBCDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mashayekhi on 13/02/2019.
 */
public class Quiz {

    private String title;

    private List<String> topics = new ArrayList<String>();
    private int difficulty;

    private List<MCQAnswer> mcqAnswers;
    private List<Answer> answers;
    private List<MCQQuestions> mcqQuestions;


    public Quiz(List<String> topics, int difficulty) {
        this.topics = topics;
        this.difficulty = difficulty;
    }

    public void takeQuiz() throws ClassNotFoundException {
        //search questions based on topics and difficulties
        // call a method from services and ask for chosen questions

        List<Question> chosenQuestions;
        QuestionJDBCDAO questionJDBCDAO = new QuestionJDBCDAO();
        chosenQuestions = questionJDBCDAO.getQuestions(difficulty);

        for (int i = 0; i < chosenQuestions.size(); i++) {
            int j = i + 1;
            System.out.println("id : " + chosenQuestions.get(i).getId());
            System.out.println("difficulty : " + chosenQuestions.get(i).getDifficulty());
            System.out.println("Q" + j + " : " + chosenQuestions.get(i).getQuestion());

            //TODO get answer from student
        }

        //TODO export
    }
}
