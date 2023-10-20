package Application;

import Trie.Trie;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
  public void insertFromCommandline(Dictionary dictionary) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < n; i++) {
      String targetWord = scanner.nextLine();
      String explainWord = scanner.nextLine();
      Word newWord = new Word(targetWord, explainWord);
      dictionary.addWord(newWord);
    }
    scanner.close();
  }

  public void loadDictionary(Dictionary dictionary) {
    dictionary.clearWordList();
    try {
      Scanner scanner = new Scanner(new File("src/main/resources/Data/dictionaries.txt"));

      while (scanner.hasNextLine()) {
        dictionary.loadWord(scanner.nextLine());
      }

      scanner.close();

      System.out.println("Dictionary loaded!");
    } catch (FileNotFoundException e) {
      System.out.println("Dictionary initiation failed.");
      e.printStackTrace();
    }
  }

  public void exportDictionary(Dictionary dictionary) {
    try {
      BufferedWriter writer =
          new BufferedWriter(new FileWriter("src/main/resources/Data/dictionaryExport.txt"));
      for (Word word : dictionary.getWordList()) {
        writer.write(word.getInfo() + "\n");
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("Dictionary export error.");
    }
  }

  public void addWord(Dictionary dictionary, Word word) {
    try {
      BufferedWriter writer =
              new BufferedWriter(new FileWriter("src/main/resources/Data/dictionaries.txt", true));
      writer.write("|" + word.getTargetWord() + "\n");
      if (!word.getIpa().isEmpty()) writer.write(word.getIpa() + "\n");
      writer.write("- " + word.getExplainWord());
      writer.newLine();
      writer.close();
      loadDictionary(dictionary);
    } catch (Exception e) {
      System.out.println("Add word error.");
    }
  }

  public void showAllWords(Dictionary dictionary, Trie trie) {
    if (dictionary.getWordList() == null) {
      return;
    }
    for (Word word : dictionary.getWordList()) {
      System.out.println(word.getTargetWord());
    }
  }

  public void search(Dictionary dictionary, String text) {

    for(String s : dictionary.prefixList(text)) {
      System.out.println(s);
    }
  }

  public void lookUp(Dictionary dictionary, String text) {
    System.out.println(dictionary.lookUp(text));
  }

}
