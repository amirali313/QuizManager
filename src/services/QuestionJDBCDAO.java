package services;

import datamodel.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class QuestionJDBCDAO {


    /*


     DELETE FROM QUESTION WHERE ID = 3;



     select * from question;*/
    private static final String INSERT_STATEMENT = "INSERT INTO QUESTION (QUESTION, DIFFICULTY) VALUES (?, ?)";
    private static final String SEARCH_STATEMENT = "SELECT * FROM QUESTION";
    private static final String UPDATE_STATEMENT = "UPDATE QUESTION SET QUESTION=?, DIFFICULTY=? WHERE ID=?";
    private static final String DELETE_STATEMENT = "DELETE FROM QUESTION WHERE ID = ?";



    public void create(Question question) {

        try (Connection connection = getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(INSERT_STATEMENT);) {

            insertStatement.setString(1, question.getQuestion());
            insertStatement.setInt(2, question.getDifficulty());

            insertStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(Question question) {



        try (Connection connection = getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_STATEMENT)){
            updateStatement.setString(1, question.getQuestion());
            updateStatement.setInt(2, question.getDifficulty());
            updateStatement.setInt(3, question.getId());
            updateStatement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Connection getConnection() throws SQLException {
        /*Configuration conf = Configuration.getInstance();
        String jdbcUrl = conf.getConfigurationValue("jdbc.url");
        String user = conf.getConfigurationValue("jdbc.user");
        String password = conf.getConfigurationValue("jdbc.password");
        Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
        return connection;*/

        String JDBC_DRIVER = "org.h2.Driver";

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            // conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/server~/test","sa","");
            conn = DriverManager.getConnection("jdbc:h2:~/test","sa","");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        return conn;
    }

    public void delete(Question question) {

        try (Connection connection = getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_STATEMENT)){
            deleteStatement.setInt(1, question.getId());
            deleteStatement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Question> search(List<String> topics, int difficulty) {

        //TODO
        //TODO search correctly
        //TODO

        List<Question> resultList = new ArrayList<Question>();

		/*SELECT
	    ID,DIFFICULTY,QUESTION
	    FROM QUESTION
	    WHERE
	       DIFFICULTY = 1
	    and
	      QUESTION LIKE '%JV%'

	      */
        String selectQuery = "select  from QUESTION WHERE ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ) {


            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {

                Question currentQuestion = new Question();
                resultList.add(currentQuestion);
            }
            results.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public List<Question> getQuestions(List<String> topics, int difficulty) {

        List<Question> resultList = new ArrayList<Question>();

        String selectQuery = "select id, question, difficulty, topic1, topic2 from question,topics where question.topicid=topics.tid and DIFFICULTY="+difficulty+" and (topic1 IN ('" + topics.get(0) +"', '"+ topics.get(1) +"') or topic2 IN ('" + topics.get(0) +"', '"+ topics.get(1) +"'))";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)
        ) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                Question question = new Question();
                question.setQuestion(results.getString("question"));
                question.setId(results.getInt("id"));
                question.setDifficulty(results.getInt("difficulty"));
                question.setTopics(topics);

                	//preparedStatement.setString(1, question.getQuestion());
                	//preparedStatement.setInt(2, question.getDifficulty());
                resultList.add(question);
            }
            results.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}