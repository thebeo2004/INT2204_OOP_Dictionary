package Application;

import java.sql.*;
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
            String explain = "@" + word.getIpa() + "\n" + "*" + word.getFunction() + "\n" + word.getExplainWord();
            String query = "INSERT INTO " + table + " (id, target, definition) VALUES (null, '" + word.getTargetWord() + "', '" + explain + "');";
            try {
                Class.forName("org.sqlite.JDBC");

                connection = DriverManager.getConnection(url);
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.execute();
//                Statement statement = connection.createStatement();
//
//                statement.executeUpdate(query);

                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void deleteWord(Dictionary dictionary, String text) {
        super.deleteWord(dictionary, text);
        String query = "DELETE FROM " + table + " WHERE target = '" + text + "';";

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();

//            Statement statement = connection.createStatement();
//
//            statement.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editWord(Dictionary dictionary, Word word) {
        super.editWord(dictionary, word);
        String query = "UPDATE " + table + " SET definition = '" + word.getExplainWord() + "' WHERE target = '" + word.getTargetWord() + "';";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url);

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();

//            Statement statement = connection.createStatement();
//
//            statement.executeUpdate(query);

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

            resultSet = statement.executeQuery("select * from " + table
                    + " where length(target) <= " + String.valueOf(length)
                    + " and length(target) >= " + String.valueOf(3));

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
