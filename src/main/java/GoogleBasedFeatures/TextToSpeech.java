package GoogleBasedFeatures;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javazoom.jl.player.Player;

public class TextToSpeech {

    public void speak(String text, String language) {
        try {
            URL url = new URL("https://translate.google.com/translate_tts?ie=UTF-8&tl=" +
                    language +
                    "&client=tw-ob&q=" + URLEncoder.encode(text, StandardCharsets.UTF_8));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream audio = connection.getInputStream();
            Player player = new Player(audio);
            player.play();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void speakEn(String text) {
        speak(text, "en");
    }

    public void speakVi(String text) {
        speak(text, "vi");
    }

}
