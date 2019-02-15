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

    private MCQAnswer mcqAnswer;
    private Answer answer;
    private MCQQuestions mcqQuestions;


    public Quiz(List<String> topics, int difficulty) {
        this.topics = topics;
        this.difficulty = difficulty;
    }

    public void takeQuiz(){
        //search questions based on topics and difficulties
        // call a method from services and ask for chosen questions

        List<Question> chosenQuestions = new ArrayList<Question>();
        QuestionJDBCDAO questionJDBCDAO = new QuestionJDBCDAO();
        chosenQuestions = questionJDBCDAO.search(topics, difficulty);

        //for
        //diplay question
        //diplay chosenQuestions[i].getquestionanswer()

    }
}
