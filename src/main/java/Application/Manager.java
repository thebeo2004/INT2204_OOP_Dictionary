package Application;

import java.util.List;

public abstract class Manager {
    public void loadDictionary(Dictionary dictionary) {};

    public String showAllWords(Dictionary dictionary) {
        String ans = "";
        for (Word word : dictionary.getWordList()) {
            if (dictionary.getId(word.getTargetWord()) != -1) {
                ans += word.getTargetWord() + "\n";
            }
        }

        return ans;
    }

    public List<String> search(Dictionary dictionary, String text) {
        return dictionary.prefixList(text);
    }

    public Word lookUp(Dictionary dictionary, String text) {
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
