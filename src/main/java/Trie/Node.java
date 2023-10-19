package Trie;

public class Node {
  static final int ALPHABET_SIZE = 26;
  private boolean isWord;
  private int order;
  public Node[] children = new Node[ALPHABET_SIZE];

  public Node() {
    isWord = false;

    for(int i = 0; i < ALPHABET_SIZE; i++) {
      children[i] = null;
    }
  }

  public void setWord() {
    isWord = true;
  }

  public Node update(char c) {
    int id = (int)c - (int)'a';

    if (children[id] == null) {
      children[id] = new Node();
    }

    return  children[id];
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getOrder() {
    return order;
  }

  public boolean getIsWord() {
    return  isWord;
  }
}
