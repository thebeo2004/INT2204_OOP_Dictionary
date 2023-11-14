package Application;

import java.util.ArrayList;
import java.util.List;

public class FlashCardManagement extends DatabaseManagement{
    public FlashCardManagement() {
        this.setUrl("jdbc:sqlite:src/main/resources/Data/flashcards.db");
        this.setTable("flashcard");
    }

    public List<Word> getFlashCards(Dictionary dictionary) {
        List<Word> storage = new ArrayList<Word>();
        for(Word word : dictionary.getWordList()) {
            if (dictionary.getId(word.getTargetWord()) != -1) {
                storage.add(word);
            }
        }
        return storage;
    }
}
