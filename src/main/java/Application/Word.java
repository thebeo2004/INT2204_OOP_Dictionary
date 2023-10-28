package Application;

public class Word {

  private String targetWord;
  private String ipa;
  private String function;
  private String explainWord;

  public Word(String targetWord) {
    this.targetWord = targetWord;
    this.ipa = "";
    this.function = "";
    this.explainWord = "";
  }

  public Word(String targetWord, String explainWord) {
    this.targetWord = targetWord;
    this.ipa = "";
    this.function = "";
    this.explainWord = explainWord;
  }

  public Word(String targetWord, String explainWord, String ipa) {
    this.targetWord = targetWord;
    this.ipa = ipa;
    this.explainWord = explainWord;
  }

  public Word(String targetWord, String explainWord, String ipa, String function) {
    this.targetWord = targetWord;
    this.ipa = ipa;
    this.function = function;
    this.explainWord = explainWord;
  }

  public String getTargetWord() {
    return targetWord;
  }

  public void setTargetWord(String targetWord) {
    this.targetWord = targetWord;
  }

  public String getIpa() {
    return ipa;
  }

  public void setIpa(String ipa) {
    this.ipa = ipa;
  }

  public String getExplainWord() {
    return explainWord;
  }

  public void setExplainWord(String explainWord) {
    this.explainWord = explainWord + "\n";
  }

  public String getFunction() {
    return function;
  }

  public void setFunction(String function) {
    this.function = function;
  }

  public String getInfo() {
    String ans = targetWord;
    if (ipa != null && !ipa.isEmpty()) ans += "\n" + ipa;
    if (function != null && !function.isEmpty()) ans += "\n" + function;
    if (explainWord != null && !explainWord.isEmpty()) ans += "\n" + explainWord;
    return ans;
  }

}
