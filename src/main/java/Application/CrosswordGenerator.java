package Application;

import java.util.*;
import static GUI.Utility.*;

public class CrosswordGenerator {
    private List<Word> wordHugeList;
    private List<Word> wordList;
    private Random rand = new Random();
    private Word solutionWord;
    private Word[] chosenWords;
    private int length = 5;

    public CrosswordGenerator() {
        wordHugeList = databaseManagement.loadCrosswordList(length);
        wordList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            int index = rand.nextInt(wordHugeList.size());
            Word word = wordHugeList.get(index);
            if (!wordList.contains(word) && !word.getTargetWord().contains("-")) {
                wordList.add(word);
            }
        }
    }

    public void buildCrossword() {


        solutionWord = wordList.get(rand.nextInt(wordList.size()));
        String target = solutionWord.getTargetWord();
        System.out.println(target);
        chosenWords = new Word[target.length()];

        int highestIndex = 0;

        for (int i = 0; i < target.length(); i++) {
            char character = target.charAt(i);
            Word word = new Word(wordList.get(rand.nextInt(wordList.size())).getTargetWord(),
                    wordList.get(rand.nextInt(wordList.size())).getExplainWord());
            int charIndex = word.getTargetWord().indexOf(character);

            if (charIndex >= 0) {
                chosenWords[i] = word;
            } else {
                i--;
            }

            if (charIndex > highestIndex) {
                highestIndex = charIndex;
            }
        }

        for (int i = 0; i < target.length(); i++) {
            char character = target.charAt(i);
            int charIndex = chosenWords[i].getTargetWord().indexOf(character);

            String temp = chosenWords[i].getTargetWord();
            String ansWord = resultStringFill(highestIndex - charIndex, ' ') + temp;
            chosenWords[i].setTargetWord(ansWord);
        }


    }

    public String resultStringFill(int count, char character) {
        char[] word = new char[count];
        Arrays.fill(word, character);
        return new String(word);
    }

    public void print() {
        for (Word word : chosenWords) {
            System.out.println(word.getTargetWord());
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<String> getCrossword() {
        List<String> crossword = new ArrayList<>();
        for (Word word : chosenWords) {
            crossword.add(word.getTargetWord());
        }
        return crossword;
    }
}