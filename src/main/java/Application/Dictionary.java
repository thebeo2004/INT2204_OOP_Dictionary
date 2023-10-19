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
      } else {
        wordList.get(length - 1).setExplainWord(data);
      }
    }
  }
  public void addWord(Word word) {
    wordList.add(word);
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
}
