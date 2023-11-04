package Application;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement extends Manager {

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

  @Override
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
      if (!word.getFunction().isEmpty()) writer.write('*' + word.getFunction() + "\n");
      writer.write(word.getExplainWord());
    } catch (Exception e) {
      System.out.println("Writer error.");
    }

  }

  private void updateDictionary(Dictionary dictionary) {
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter("src/main/resources/Data/dictionaries.txt/", false));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (Word word : dictionary.getWordList()) {
      if (dictionary.getId(word.getTargetWord()) != -1) {
        write(writer, word);
      }
    }
    try {
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void addWord(Dictionary dictionary, Word word) {
    if (lookUp(dictionary, word.getTargetWord()) == null) {
      dictionary.addWord(word);
      updateDictionary(dictionary);
    }
  }

  @Override
  public void deleteWord(Dictionary dictionary, String text) {
    super.deleteWord(dictionary, text);
    updateDictionary(dictionary);

  }

  @Override
  public void editWord(Dictionary dictionary, Word word) {
    super.editWord(dictionary, word);
    updateDictionary(dictionary);
  }

}
