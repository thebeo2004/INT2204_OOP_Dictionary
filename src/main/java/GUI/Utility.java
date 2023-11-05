package GUI;

import Application.DatabaseManagement;
import Application.Dictionary;
import Application.DictionaryManagement;
import java.util.ArrayList;
import java.util.List;

public class Utility {
  public static Dictionary dictionary = new Dictionary();
  public static DatabaseManagement databaseManagement = new DatabaseManagement();
  public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
  public static List<String> searchingHistory = new ArrayList<String>();
}
