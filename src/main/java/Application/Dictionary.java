package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {

  List<Word> wordList = new ArrayList<Word>();

  private void process(String data) {

    if (data.isEmpty()) {
      return;
    }

    if (data.charAt(0) == '|') {
      data = data.replace("|", "");
      wordList.add(new Word(data));
    } else {
      int length = wordList.size();

      if (data.charAt(0) == '/') {
        wordList.get(length - 1).setIpa(data);
      } else {
        wordList.get(length - 1).setExplainWord(data);
      }
    }
  }

  public Dictionary() {
    try {
      Scanner scanner = new Scanner(new File("src/main/resources/Data/dictionaries.txt"));

      while(scanner.hasNextLine()) {
        process(scanner.nextLine());
      }

      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occured.");
      e.printStackTrace();
    }
  }

  public void show() {

    for(Word w: wordList) {
      System.out.println(w.getInfor());
    }
  }
}
