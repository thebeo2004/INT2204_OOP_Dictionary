package Application;

public class Word {

  private String targetWord;
  private String ipa;
  private String explainWord;

  public Word(String targetWord) {
    this.targetWord = targetWord;
    this.ipa = "";
    this.explainWord = "";
  }

  public Word(String targetWord, String explainWord) {
    this.targetWord = targetWord;
    this.ipa = "";
    this.explainWord = explainWord;
  }

  public Word(String targetWord, String explainWord, String ipa) {
    this.targetWord = targetWord;
    this.ipa = ipa;
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
    this.explainWord += explainWord + "\n";
  }

  public String getInfo() {
    return targetWord + "\n" + ipa + "\n" + explainWord;
  }

}
