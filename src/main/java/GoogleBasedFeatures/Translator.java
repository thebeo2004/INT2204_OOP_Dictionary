package GoogleBasedFeatures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Translator {

  public Translator() {};

  public String translate(String text, String target, String explain) {
    StringBuilder response = new StringBuilder();
    try {
      String urlStr =
          "https://script.google.com/macros/s/AKfycbySjXbo-BOQvv2toFcle3m9Fx0bj_2XqXtyO3pA5oCXC7iMPPNSONe9tCFWC_sKBl1mGw/exec"
              + "?q="
              + URLEncoder.encode(text, StandardCharsets.UTF_8)
              + "&target="
              + explain
              + "&source="
              + target;
      URL url = new URL(urlStr);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0");
      BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String output = "";
      while ((output = input.readLine()) != null) {
        response.append(output);
      }
      input.close();
    } catch (IOException exception) {
      System.out.println("Failed to translate");
    }
    return response.toString();
  }
}
