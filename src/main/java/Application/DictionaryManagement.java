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

  public void write(BufferedWriter writer, Word word) {
    try {
      writer.write("|" + word.getTargetWord() + "\n");
      if (!word.getIpa().isEmpty()) writer.write(word.getIpa() + "\n");
      if (!word.getFunction().isEmpty()) writer.write("*" + word.getFunction() + "\n");
      int n = word.getExplainWord().length();
      writer.write("-" + word.getExplainWord());
      //writer.newLine();
    } catch (Exception e) {
      System.out.println("Writer error.");
    }

  }

//  public void exportDictionary(Dictionary dictionary, String file) {
//    try {
//      BufferedWriter writer =
//          new BufferedWriter(new FileWriter(file));
//      for (Word word : dictionary.getWordList()) {
//        write(writer, word);
//      }
//      writer.close();
//    } catch (Exception e) {
//      System.out.println("Dictionary export error.");
//    }
//  }

//  public void exportDictionary(Dictionary dictionary) {
//    exportDictionary(dictionary, "src/main/resources/Data/dictionaryExport.txt");
//  }

  private void updateDictionary(Dictionary dictionary) {

    for(Word word : dictionary.getWordList()) {
      if (dictionary.getId(word.getTargetWord()) != -1) {
        //Task of NDH: write word to dictionary.txt
        write();
      }
    }
  }

  public void addWord(Dictionary dictionary, Word word) {
//    try {
//      BufferedWriter writer =
//              new BufferedWriter(new FileWriter("src/main/resources/Data/dictionaries.txt", true));
//      write(writer, word);
//      writer.write("\n");
//      writer.close();

      dictionary.addWord(word);
      updateDictionary(dictionary);
//    } catch (Exception e) {
//      System.out.println("Add word error.");
//    }
  }

  public void deleteWord(Dictionary dictionary, String text) {
    int id = dictionary.getId(text);
    if (id == -1) return;

    dictionary.deleteWord(text);
    updateDictionary(dictionary);
//    exportDictionary(dictionary, "src/main/resources/Data/dictionaries.txt");
  }

  public void editWord(Dictionary dictionary, Word word) {

    int id = dictionary.getId(word.getTargetWord());
    if (id == -1) {
      return;
    }
    dictionary.editWord(word);
    updateDictionary(dictionary);
  }

  public void showAllWords(Dictionary dictionary) {
    for (Word word : dictionary.getWordList()) {
      System.out.println(word.getTargetWord());
    }
  }

  public void print(Dictionary dictionary) {
    for (Word word : dictionary.getWordList()) {
      System.out.println(word.getInfo());
    }
  }

  public void search(Dictionary dictionary, String text) {
    for(String s : dictionary.prefixList(text)) {
      System.out.println(s);
    }
  }

  public String lookUp(Dictionary dictionary, String text) {
    return dictionary.lookUp(text);
  }

}
