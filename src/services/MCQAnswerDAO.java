package services;

import datamodel.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mashayekhi on 18/02/2019.
 */
public class MCQAnswerDAO {

    public static final String CREATE_QUERY = "INSERT INTO QUESTION (CH1, CH2, CH3, CH4, ANSWER) VALUES (?,?,?,?,?)";
    public static final String UPDATE_QUERY = "UPDATE QUESTION SET QUESTION=?, DIFFICULTY=?, CH1=?, CH2=?, CH3=?, CH4=?, ANSWER=? WHERE ID=?";


    private Connection getConnection() throws SQLException {

        Configuration conf = Configuration.getInstance();
        String dburl = conf.getPropertyValue("db.url");
        String user = conf.getPropertyValue("db.username");
        String password = conf.getPropertyValue("db.password");
        Connection connection = DriverManager.getConnection(dburl, user, password);
        return connection;

    }

    public void create(Question question) {

        try (Connection connection = getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(CREATE_QUERY)) {
            insertStatement.setString(1, question.getMcqAnswers().get(0));
            insertStatement.setString(2, question.getMcqAnswers().get(1));
            insertStatement.setString(3, question.getMcqAnswers().get(2));
            insertStatement.setString(4, question.getMcqAnswers().get(3));
            insertStatement.setInt(5, question.getValidChoice());
            insertStatement.execute();
            System.out.println("Answer created Successfully!!!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(Question question) {

        try (Connection connection = getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_QUERY)){
            updateStatement.setString(1, question.getQuestion());
            updateStatement.setInt(2, question.getDifficulty());
            updateStatement.setString(3, question.getMcqAnswers().get(0));
            updateStatement.setString(4, question.getMcqAnswers().get(1));
            updateStatement.setString(5, question.getMcqAnswers().get(2));
            updateStatement.setString(6, question.getMcqAnswers().get(3));
            updateStatement.setInt(7, question.getValidChoice());
            updateStatement.setInt(8, question.getId());
            updateStatement.executeUpdate();
            System.out.println("Update Successfully :)");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
