package Application;

import java.io.*;

public class GameResult {
  private int trophies;
  private int stars;

  public GameResult() {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader("src/main/resources/Data/progress.txt/"));
      trophies = Integer.parseInt(reader.readLine());
      stars = Integer.parseInt(reader.readLine());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      reader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public int getTrophies() {
    return trophies;
  }

  public int getStars() {
    return stars;
  }

  public void setScore(int trophies, int stars) {
    this.trophies = trophies;
    this.stars = stars;
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Data/progress.txt/", false));
      writer.write(String.valueOf(trophies));
      writer.write("\n");
      writer.write(String.valueOf(stars));
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
