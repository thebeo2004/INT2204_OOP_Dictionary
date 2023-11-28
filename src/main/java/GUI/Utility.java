package GUI;

import Application.*;
import GoogleBasedFeatures.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utility {
  public static Dictionary dictionary = new Dictionary();
  public static DatabaseManagement databaseManagement = new DatabaseManagement();
  public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
  public static CrosswordGenerator crosswordGenerator = new CrosswordGenerator();
  public static Translator translator = new Translator();
  public static TextToSpeech textToSpeech = new TextToSpeech();
  public static Thesaurus thesaurus = new Thesaurus();

  public static Dictionary flashCards = new Dictionary();
  public static FlashCardManagement flashCardManagement = new FlashCardManagement();

  public static List<String> searchingHistory = new ArrayList<String>();

  public static List<String> acceptedCharacter = new ArrayList<>();

  public static List<Integer> generateNumber(int length) {
    List<Integer> ans = new ArrayList<>();
    Random rand = new Random();
    int num = Math.max(1,rand.nextInt((int)length/2));
    while (ans.size() < num) {
      int x = rand.nextInt(length);
      if(!ans.contains(x)) {
        ans.add(x);
      }
    }

    return ans;
  }

}
