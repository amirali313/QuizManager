package datamodel;


import java.util.List;

public class Student {

    private String name;
    private String id;

    private List<MCQAnswer> mcqAnswer;
    private List<Answer> answer;

    private List<Quiz> quiz;

    private List<Question> questions;

    private int grade;


    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<MCQAnswer> getMcqAnswer() {
        return mcqAnswer;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public List<Quiz> getQuiz() {
        return quiz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMcqAnswer(List<MCQAnswer> mcqAnswer) {
        this.mcqAnswer = mcqAnswer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
