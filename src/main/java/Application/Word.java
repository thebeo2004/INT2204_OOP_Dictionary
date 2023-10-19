package Application;

public class Word {

  private String targetWord;
  private String ipa;
  private String explainWord;

  public Word(String targetWord) {
    this.targetWord = targetWord;
    ipa = "";
    explainWord = "";
  }

  public void setIpa(String ipa) {
    this.ipa = ipa;
  }

  public void setExplainWord(String explainWord) {
    this.explainWord += explainWord + "\n";
  }

  public String getInfor() {
    return targetWord + "\n" + ipa + "\n" + explainWord;
  }
}

