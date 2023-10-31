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
            if (dictionary.getId(word.getTargetWord()) != -1) {
                System.out.println(word.getTargetWord());
            }
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

    public void editWord(Dictionary dictionary, Word word) {
        int id = dictionary.getId(word.getTargetWord());
        if (id == -1) {
            return;
        }
        dictionary.editWord(word);
    }

    public void deleteWord(Dictionary dictionary, String text) {
        int id = dictionary.getId(text);
        if (id == -1) return;
        dictionary.deleteWord(text);
    }
}
