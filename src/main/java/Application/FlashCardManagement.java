package Application;

import java.util.ArrayList;
import java.util.List;

public class FlashCardManagement extends DatabaseManagement{
    public FlashCardManagement() {
        this.setUrl("jdbc:sqlite:src/main/resources/Data/flashcards.db");
        this.setTable("flashcard");
    }

    public List<Word> getFlashCards(Dictionary dictionary) {
        return dictionary.getWordList();
    }
}
