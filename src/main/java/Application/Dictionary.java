package Application;

import Trie.Trie;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

  List<Word> wordList = new ArrayList<Word>();
  Trie trie = new Trie();

  public Dictionary() {
  }

  protected void loadWord(String data) {

    if (data.isEmpty()) {
      return;
    }

    if (data.charAt(0) == '|') {
      data = data.replace("|", "");
      wordList.add(new Word(data));

      int length = wordList.size();
      trie.insert(data, length - 1);
    } else {
      int length = wordList.size();

      if (data.charAt(0) == '/') {
        wordList.get(length - 1).setIpa(data);
      } else if (data.charAt(0) == '*') {
        data = data.substring(1);
        wordList.get(length - 1).setFunction(data);
      } else {
        wordList.get(length - 1).setExplainWord(data);
      }
    }
  }
  public void addWord(Word word) {
    wordList.add(word);

    int length = wordList.size();
    trie.insert(word.getTargetWord(), length - 1);
  }

  public void deleteWord(String text) {
    trie.deleteWord(text);
  }

  public void editWord(Word word) {
    int id = getId(word.getTargetWord());

    wordList.get(id).setIpa(word.getIpa());
    wordList.get(id).setFunction(word.getFunction());
    wordList.get(id).setExplainWord(word.getExplainWord());
  }

  public List<Word> getWordList() {
    return wordList;
  }

  public void clearWordList() {
    wordList.clear();
  }

  public List<String> prefixList(String text) {

    List<String> storage = new ArrayList<String>();

    for(Integer i : trie.matchingPrefix(text)) {
      storage.add(wordList.get(i).getTargetWord());
    }

    return storage;
  }

  public int getId(String text) {
    return trie.lookUp(text);
  }

  public String lookUp(String text) {
    int id = getId(text);

    if (id == -1) {
      return "This word doesn't exist";
    }
    return wordList.get(id).getInfo();
  }
}
