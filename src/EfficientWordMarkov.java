import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }

    public EfficientWordMarkov() {
        this(3);
    }

    @Override
    public ArrayList<String> getFollows(WordGram key) {
        if (!myMap.containsKey(key)) {
            throw new NoSuchElementException(key + " not in map");
        }
        return myMap.get(key);
    }

    @Override
    public void setTraining(String text) {
        myWords = text.split("\\s+");
        myMap.clear();
        for (int i = 0; i < myWords.length - myOrder; i++) {
            WordGram word = new WordGram(myWords, i, myOrder);
            myMap.putIfAbsent(word, new ArrayList<String>());
            myMap.get(word).add(myWords[i + myOrder]);
        }
        WordGram lastWord = new WordGram(myWords, myWords.length - myOrder, myOrder);
        myMap.putIfAbsent(lastWord, new ArrayList<String>());
        myMap.get(lastWord).add(PSEUDO_EOS);
    }
}
