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

  public void setText(String text) {
    String temp = text.toLowerCase();
    this.text = text.toUpperCase();
    Thread thread = new Thread(() -> {
      this.explain = translator.translate(temp, "en", "vi");
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
