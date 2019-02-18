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

    private static final String INSERT_STATEMENT1 = "INSERT INTO QUESTION (QUESTION, DIFFICULTY) VALUES (?, ?)";
    private static final String INSERT_STATEMENT2 = "INSERT INTO TOPICS (TOPIC1, TOPIC2) VALUES (?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM QUESTION WHERE ID = ?";
    private static final String SEARCH_QUERY = "select id, question, difficulty, topic1, topic2 from question,topics where question.topicid=topics.tid and DIFFICULTY=? and (topic1 IN (?,?) or topic2 IN (?,?))";
    private static final String SHOWALL_QUERY = "SELECT ID, QUESTION, DIFFICULTY, TOPIC1, TOPIC2 FROM QUESTION Q,TOPICS T WHERE Q.TOPICID=T.TID";
    private static final String UPDATE_QUERY = "UPDATE QUESTION SET QUESTION=?, DIFFICULTY=? WHERE ID=?";


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
             PreparedStatement insertStatement1 = connection.prepareStatement(INSERT_STATEMENT1);
             PreparedStatement insertStatement2 = connection.prepareStatement(INSERT_STATEMENT2)	) {
            insertStatement1.setString(1, question.getQuestion());
            insertStatement1.setInt(2, question.getDifficulty());
            insertStatement2.setString(1, question.getTopics().get(0));
            insertStatement2.setString(2, question.getTopics().get(1));
            insertStatement1.execute();
            insertStatement2.execute();
            System.out.println("Question created Successfully!!!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(Question question) {



        try (Connection connection = getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_QUERY)){
            updateStatement.setString(1, question.getQuestion());
            updateStatement.setInt(2, question.getDifficulty());
            updateStatement.setInt(3, question.getId());
            updateStatement.executeUpdate();
            System.out.println("Update Successfully :)");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Question question) {

        try (Connection connection = getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_QUERY)){
            deleteStatement.setInt(1, question.getId());
            deleteStatement.execute();
            System.out.println("Deleted successfully!!");
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

        /*//String selectQuery = "select id, question, difficulty, topic1," +
                " topic2 from question,topics where question.topicid=topics.tid and " +
                "DIFFICULTY="+difficulty+" and (topic1 IN ('" + topics.get(0) +"', '"+ topics.get(1) +"')" +
                " or topic2 IN ('" + topics.get(0) +"', '"+ topics.get(1) +"'))";*/

        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);
            preparedStatement.setInt(1, difficulty);
            preparedStatement.setString(2, topics.get(0));
            preparedStatement.setString(3, topics.get(1));
            preparedStatement.setString(4, topics.get(0));
            preparedStatement.setString(5, topics.get(1));

            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                Question question = new Question();
                question.setQuestion(results.getString("question"));
                question.setId(results.getInt("id"));
                question.setDifficulty(results.getInt("difficulty"));
                question.setTopics(topics);

                resultList.add(question);
            }
            results.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() == 0){
            System.out.println("No Quiz found :(");
        }
        return resultList;
    }

    public List<Question> showAllQuestions() {

        List<Question> resultList = new ArrayList<Question>();

        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SHOWALL_QUERY);
            /*preparedStatement.setInt(1, difficulty);
            preparedStatement.setString(2, topics.get(0));
            preparedStatement.setString(3, topics.get(1));
            preparedStatement.setString(4, topics.get(0));
            preparedStatement.setString(5, topics.get(1));*/

            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                List<String> topics = new ArrayList<>();

                Question question = new Question();
                question.setQuestion(results.getString("question"));
                question.setId(results.getInt("id"));
                question.setDifficulty(results.getInt("difficulty"));
                topics.add(results.getString("topic1"));
                topics.add(results.getString("topic2"));
                question.setTopics(topics);

                resultList.add(question);
            }
            results.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() == 0){
            System.out.println("Could Not Show Question List :(");
        }
        return resultList;
    }
}