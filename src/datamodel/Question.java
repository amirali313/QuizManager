package datamodel;
import java.util.List;

public class Question {

    private int id;

    private String question;
    private List<String> topics;
    private int difficulty;

    private List<String> mcqAnswers;
    private int validChoice;

    @Override
    public String toString() {
        return "Question [id=" + id + ", question=" + question + ", topics=" + topics + ", difficulty=" + difficulty
                + "]";
    }

    public Question() {
    }

//    public Question(String question, List<String> topics, Integer difficulty) {
//        this.question = question;
//        this.topics = topics;
//        this.difficulty = difficulty;
//    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getMcqAnswers() {
        return mcqAnswers;
    }

    public void setMcqAnswers(List<String> mcqAnswers) {
        this.mcqAnswers = mcqAnswers;
    }

    public int getValidChoice() {
        return validChoice;
    }

    public void setValidChoice(int validChoice) {
        this.validChoice = validChoice;
    }
}

