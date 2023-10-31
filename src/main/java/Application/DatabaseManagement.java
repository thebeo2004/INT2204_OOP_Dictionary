package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManagement extends Manager {

    private String url = "jdbc:sqlite:D:/demo/demo/src/main/resources/Data/envidict.db";
    private String username = "root";
    private String password = "hieu1804bgg";
    private Connection connection = null;
    private ResultSet resultSet = null;

    @Override
    public void loadDictionary(Dictionary dictionary) {
        dictionary.clearWordList();

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from dictionary");

            while (resultSet.next()) {
                Word word = new Word(resultSet.getString("target"), resultSet.getString("definition"));
                if (word.getTargetWord() == null) continue;
                dictionary.addWord(word);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWord(Dictionary dictionary, Word word) {
        if (lookUp(dictionary, word.getTargetWord()).equals("This word doesn't exist")) {
            dictionary.addWord(word);
            String query = "INSERT INTO dictionary (id, target, definition) VALUES (null, '" + word.getTargetWord() + "', '" + word.getExplainWord() + "');";
            try {
                Class.forName("org.sqlite.JDBC");

                connection = DriverManager.getConnection(url);

                Statement statement = connection.createStatement();

                statement.executeUpdate(query);

                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteWord(Dictionary dictionary, String text) {
        super.deleteWord(dictionary, text);
        String query = "DELETE FROM dictionary WHERE target = '" + text + "';";
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editWord(Dictionary dictionary, Word word) {
        super.editWord(dictionary, word);
        //dictionary.editWord(word);
        String query = "UPDATE dictionary SET definition = '" + word.getExplainWord() + "' WHERE target = '" + word.getTargetWord() + "';";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
