package Application;

public abstract class Manager {
    public void loadDictionary(Dictionary dictionary) {};

    public void showAllWords(Dictionary dictionary) {
        for (Word word : dictionary.getWordList()) {
            System.out.println(word.getTargetWord());
        }
    }

    public void print(Dictionary dictionary) {
        for (Word word : dictionary.getWordList()) {
            System.out.println(word.getInfo());
        }
    }

    public void search(Dictionary dictionary, String text) {
        for(String s : dictionary.prefixList(text)) {
            System.out.println(s);
        }
    }

    public String lookUp(Dictionary dictionary, String text) {
        return dictionary.lookUp(text);
    }
}
