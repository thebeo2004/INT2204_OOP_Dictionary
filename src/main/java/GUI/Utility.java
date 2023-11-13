package GUI;

import Application.*;
import GoogleBasedFeatures.*;
import java.util.ArrayList;
import java.util.List;

public class Utility {
  public static Dictionary dictionary = new Dictionary();
  public static DatabaseManagement databaseManagement = new DatabaseManagement();
  public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
  public static Translator translator = new Translator();
  public static TextToSpeech textToSpeech = new TextToSpeech();
  public static Thesaurus thesaurus = new Thesaurus();

  public static Dictionary flashCards = new Dictionary();
  public static FlashCardManagement flashCardManagement = new FlashCardManagement();

  public static List<String> searchingHistory = new ArrayList<String>();
}
