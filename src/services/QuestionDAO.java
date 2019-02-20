package services;

import datamodel.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestionDAO {

    private static final String INSERT_STATEMENT1 = "INSERT INTO QUESTION (QUESTION, DIFFICULTY, TOPICID) VALUES (?, ?, ?)";
    private static final String TOPICINSERT_QUERY = "INSERT INTO TOPICS (TOPIC1, TOPIC2) VALUES (?, ?)";
    private static final String GETTOPICID_QUERY = "SELECT ID, topic1, topic2 FROM TOPICS ORDER BY ID DESC LIMIT 1;";

    private static final String DELETE_QUERY = "DELETE FROM QUESTION WHERE ID = ?";
    private static final String SEARCH_QUERY = "select question.id, question, difficulty, topic1, topic2, CH1,CH2, CH3, CH4, ANSWER from question,topics where question.topicid=topics.id and DIFFICULTY=? and (topic1 IN (?,?) or topic2 IN (?,?))";
    private static final String SHOWALL_QUERY = "SELECT q.ID, QUESTION, DIFFICULTY, TOPIC1, TOPIC2, CH1,CH2, CH3, CH4, ANSWER FROM QUESTION Q,TOPICS T WHERE Q.TOPICID=T.id";
    private static final String UPDATE_QUERY = "UPDATE QUESTION SET QUESTION=?, DIFFICULTY=? WHERE ID=?";


    public static Connection getConnection() throws SQLException {

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
             PreparedStatement topicInsertQuery = connection.prepareStatement(TOPICINSERT_QUERY);
             PreparedStatement getTopicsIdQuery = connection.prepareStatement(GETTOPICID_QUERY)) {


            topicInsertQuery.setString(1, question.getTopics().get(0));
            topicInsertQuery.setString(2, question.getTopics().get(1));
            topicInsertQuery.execute();

            ResultSet result = getTopicsIdQuery.executeQuery();
            int id=0;
            while(result.next()) {
                //System.out.println("TID: " + result);
                id = result.getInt("ID");
            }
            insertStatement1.setString(1, question.getQuestion());
            insertStatement1.setInt(2, question.getDifficulty());
            insertStatement1.setInt(3, id);
            insertStatement1.execute();

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

    public List<Question> getQuestions(List<String> topics, int difficulty) {

        List<Question> resultList = new ArrayList<Question>();

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
                List<String> choices = new ArrayList<>();
                question.setQuestion(results.getString("question"));
                question.setId(results.getInt("id"));
                question.setDifficulty(results.getInt("difficulty"));
                topics.add(results.getString("topic1"));
                topics.add(results.getString("topic2"));
                question.setTopics(topics);
                choices.add(results.getString("ch1"));
                choices.add(results.getString("ch2"));
                choices.add(results.getString("ch3"));
                choices.add(results.getString("ch4"));
                question.setMcqAnswers(choices);
                question.setValidChoice(results.getInt("answer"));
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
                List<String> choices = new ArrayList<>();
                Question question = new Question();
                question.setQuestion(results.getString("question"));
                question.setId(results.getInt("id"));
                question.setDifficulty(results.getInt("difficulty"));
                topics.add(results.getString("topic1"));
                topics.add(results.getString("topic2"));
                question.setTopics(topics);
                choices.add(results.getString("ch1"));
                choices.add(results.getString("ch2"));
                choices.add(results.getString("ch3"));
                choices.add(results.getString("ch4"));
                question.setMcqAnswers(choices);
                question.setValidChoice(results.getInt("answer"));
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