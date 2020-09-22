import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String, ArrayList<String>> myMap;

	EfficientMarkov() {
		this(3);
		myMap = new HashMap<String, ArrayList<String>>();
	}

	EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	@Override
	public void setTraining(String text) {
		// set myText to parameter text
		myText = text;

		// clear the map
		myMap.clear();

		// initialize the map

		// string
		String x;
		// next string
		String y;

		for (int i = 0; i < myText.length() + 1 - myOrder; i++) {

			// create key
			x = myText.substring(i, i + myOrder);

			// store keys
			if (!myMap.containsKey(x)) {
				myMap.put(x, new ArrayList<String>());
			}
			if (myText.length() == i + myOrder) {
				myMap.get(x).add(PSEUDO_EOS);
			} else {
				y = myText.substring(myOrder + i, myOrder + i + 1);
				myMap.get(x).add(y);
			}
		}
	}

	@Override
	public ArrayList<String> getFollows(String key) {
		// look up the key in the map
		if (!myMap.containsKey(key)) {
			// the key is not in the map
			throw new NoSuchElementException(key + " not in map");
		}
		// return associated value
			return myMap.get(key);
	}
}
