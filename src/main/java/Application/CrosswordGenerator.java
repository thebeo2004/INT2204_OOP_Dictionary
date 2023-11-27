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
    }

    public void buildCrossword() {
        for (int i = 0; i < 100; i++) {
            int index = rand.nextInt(wordHugeList.size());
            Word word = wordHugeList.get(index);
            if (!wordList.contains(word) && word.getTargetWord().matches("[a-zA-Z]+")) {
                wordList.add(word);
            }
        }
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

            if (charIndex >= 0 && !Objects.equals(word.getTargetWord(), solutionWord.getTargetWord())
                    && !checkDuplicates(chosenWords, word.getTargetWord())) {
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
        List<String> tmp = new ArrayList<>();
        for(Word w : chosenWords) {
            tmp.add(w.getTargetWord());
        }

        return tmp;
    }

    public String getSolution() {
        return solutionWord.getTargetWord();
    }

    private boolean checkDuplicates(Word[] words, String word) {
        for (Word w : words) {
            if (w != null && w.getTargetWord() != null && w.getTargetWord().equals(word)) {
                return true;
            }
        }
        return false;
    }

    public int getColumn() {
        String solution = solutionWord.getTargetWord();
        return chosenWords[0].getTargetWord().indexOf(solution.charAt(0));
    }
}