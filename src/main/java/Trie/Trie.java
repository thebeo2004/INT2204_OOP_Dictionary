package Trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
  private Node root;

  public Trie() {
    root = new Node();
  }

  public Node traversal(String txt) {
    Node curNode = root;

    for(int i = 0; i < txt.length(); i++) {
      curNode = curNode.update(txt.charAt(i));
    }

    return curNode;
  }

  public void insert(String txt, int order) {
    Node curNode = traversal(txt);
    curNode.setWord();
    curNode.setOrder(order);
  }

  private List<Integer> wordList = new ArrayList<Integer>();

  public void prefixMatchingWord(Node curNode) {

    if (wordList.size() > 100) {
      return;
    }

    if (curNode.getIsWord()) {
      wordList.add(curNode.getOrder());
    }

    for(Node i : curNode.children) {
      if (i != null) {
        prefixMatchingWord(i);
      }
    }

  }
  public List<Integer> matchingPrefix(String txt) {
    wordList.clear();
    prefixMatchingWord(traversal(txt));
    return wordList;
  }
}
