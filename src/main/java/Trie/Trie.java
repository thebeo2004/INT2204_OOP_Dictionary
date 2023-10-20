package Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
  private Node root;

  public Trie() {
    root = new Node();
  }

  public Node traversal(String text) {
    Node curNode = root;

    for (int i = 0; i < text.length(); i++) {
      curNode = curNode.update(text.charAt(i));
    }

    return curNode;
  }

  public void insert(String text, int order) {
    Node curNode = traversal(text);
    curNode.setIsWord();
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

    for (Map.Entry<Character, Node> i : curNode.hashMap.entrySet()) {
        prefixMatchingWord(i.getValue());
    }
  }

  public List<Integer> matchingPrefix(String text) {
    wordList.clear();
    prefixMatchingWord(traversal(text));
    return wordList;
  }

  public int lookUp(String text) {
    Node curNode = traversal(text);

    if (curNode.getIsWord()) {
      return curNode.getOrder();
    }

    return -1;
  }

}
