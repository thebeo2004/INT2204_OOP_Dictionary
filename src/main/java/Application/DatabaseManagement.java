package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManagement extends Manager {

    private String url = "jdbc:sqlite:src/main/resources/Data/envidict.db";
    private Connection connection = null;
    private ResultSet resultSet = null;
    private String table = "dictionary";

    protected void setUrl(String url) {
        this.url = url;
    }

    protected void setTable(String table) {
        this.table = table;
    }

    @Override
    public void loadDictionary(Dictionary dictionary) {
        dictionary.clearWordList();

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from " + table);

            while (resultSet.next()) {
                Word word = new Word(resultSet.getString("target"), resultSet.getString("definition"));

                String[] arr = resultSet.getString("definition").split("\n");

                dictionary.loadWord("|" + resultSet.getString("target"));
                for (String i : arr) {
                    dictionary.loadWord(i);
                }

                if (word.getTargetWord() == null) continue;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWord(Dictionary dictionary, Word word) {
        if (lookUp(dictionary, word.getTargetWord()) == null) {
            dictionary.addWord(word);
            String query = "INSERT INTO " + table + " (id, target, definition) VALUES (null, '" + word.getTargetWord() + "', '" + word.getExplainWord() + "');";
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

            connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Word> loadCrosswordList(int length) {

        ArrayList<Word> result = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from dictionary where length(target) <= " + String.valueOf(length));

            while (resultSet.next()) {
                Word word = new Word(resultSet.getString("target"), resultSet.getString("definition"));


                result.add(word);

                if (word.getTargetWord() == null) continue;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
