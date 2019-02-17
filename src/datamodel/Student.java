package datamodel;


import java.util.List;

public class Student {

    private String name;
    private String id;

    private List<MCQAnswer> mcqAnswer;
    private List<Answer> answer;

    private List<Quiz> quiz;


    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
