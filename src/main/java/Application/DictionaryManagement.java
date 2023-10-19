package Application;

import java.util.Scanner;

public class DictionaryManagement {
  public static void insertFromCommandline(Dictionary dictionary) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < n; i++) {
      String targetWord = scanner.nextLine();
      String explainWord = scanner.nextLine();
      Word newWord = new Word(targetWord, explainWord);
      dictionary.addWord(newWord);
      System.out.println(newWord.toString());
    }
    scanner.close();
  }

  public static void showAllWords(Dictionary dictionary) {
    for (Word word : dictionary.getWordList()) {
      System.out.println(word.getInfo());
    }
    return;
  }
}
