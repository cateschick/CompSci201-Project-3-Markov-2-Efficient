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

    // getFollows is the same as in EfficientMarkov, except myMap is different
    @Override
    public ArrayList<String> getFollows(WordGram key) {
        if (!myMap.containsKey(key)) {
            throw new NoSuchElementException(key + " not in map");
        }
        return myMap.get(key);
    }

    @Override
    public void setTraining(String text) {
        // create an array of words
        myWords = text.split("\\s+");

        // clear the map
        myMap.clear();

        // same general idea as Efficient Markov
        for (int i = 0; i < myWords.length - myOrder; i++) {

            // create a new wordgram instead of substring
            WordGram word = new WordGram(myWords, i, myOrder);

            myMap.putIfAbsent(word, new ArrayList<String>());
            myMap.get(word).add(myWords[i + myOrder]);

        }
        WordGram lastWord = new WordGram(myWords, myWords.length - myOrder, myOrder);
        myMap.putIfAbsent(lastWord, new ArrayList<String>());
        myMap.get(lastWord).add(PSEUDO_EOS);
    }
}
