package Application;
import static GUI.Utility.*;

public class Puzzle {
  private int x;
  private int y;
  private String text;
  private String explain;

  public int getLength() {
    return text.length();
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  private String getIpa(String text) {
    Word w = databaseManagement.lookUp(dictionary, text);
    String tmp = w.getIpa();
    String ans = "";
    int cnt = 0;
    for(int i = 0; i < tmp.length(); i++) {
      if (tmp.charAt(i) == '/') {
        cnt++;
      }

      if (cnt > 0) {
        ans += tmp.charAt(i);
      }

      if (cnt == 2) {
        return ans;
      }
    }

    return ans;
  }

  public void setText(String text) {
    String temp = text.toLowerCase();
    this.text = text.toUpperCase();
    this.explain = getIpa(text);

    if (!this.explain.isEmpty()) {
      this.explain += ", ";
    }

    Thread thread = new Thread(() -> {
      this.explain += translator.translate(temp, "en", "vi");
    });
    thread.start();
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public String getText() {
    return text;
  }

  public String getExplain() {
    return explain;
  }
}
