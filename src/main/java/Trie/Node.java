package Trie;

import java.util.HashMap;

public class Node {
  private boolean isWord;
  private int order;
  public HashMap<Character, Node> hashMap;

  public Node() {
    isWord = false;
    hashMap = new HashMap<Character, Node>();
  }

  public void setIsWord() {
    isWord = true;
  }

  public void setNotWord() {
    isWord = false;
  }

  public Node update(char c) {

    if (hashMap.get(c) == null) {
      hashMap.put((Character) c, new Node());
    }

    return hashMap.get(c);
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getOrder() {
    return order;
  }

  public boolean getIsWord() {
    return isWord;
  }
}
