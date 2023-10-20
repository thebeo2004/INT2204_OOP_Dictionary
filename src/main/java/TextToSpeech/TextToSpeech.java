package TextToSpeech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
  Voice voice;

  public TextToSpeech() {
    System.setProperty(
        "freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    voice = VoiceManager.getInstance().getVoice("kevin16");
    if (voice != null) {
      voice.allocate();
      try {
        voice.setRate(120);
        voice.setPitch(150);
        voice.setVolume(3);
      } catch (Exception e1) {
        e1.printStackTrace();
      }

    } else {
      throw new IllegalStateException("Cannot find voice: kevin16");
    }
  }

  public void speak(String words) {
    voice.speak(words);
  }
}
