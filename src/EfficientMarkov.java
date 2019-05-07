import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class EfficientMarkov implements MarkovInterface<String> {
	private String myText;
	private Random myRandom;
	private int myOrder;
	// calling an instance variable.
	private HashMap<String, ArrayList<String>> MarkovMap;

	private static String PSEUDO_EOS = "";
	private static int RANDOM_SEED = 1234;

	public EfficientMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}

	public EfficientMarkov() {
		this(3);
	}

	public void setTraining(String text) {
		myText = text;
		
		// Since you initialized the global variable, don't need to do the normal initialization.
		MarkovMap = new HashMap<>();

		// having arrayList in for loop wipes it clean.
		// ArrayList<String> current = new ArrayList<>();

// Figured this out
		
		for (int j = 0; j < (text.length()) - myOrder; j++) {

			if (MarkovMap.containsKey(text.substring(j, j + (myOrder))) == false) {
				MarkovMap.put(text.substring(j, j + (myOrder)), new ArrayList<String>());
				MarkovMap.get(text.substring(j, j + myOrder)).add(text.substring(j + myOrder, j + (myOrder + 1)));
			} else if (MarkovMap.containsKey(text.substring(j, j + myOrder))) {
				MarkovMap.get(text.substring(j, j + myOrder)).add(text.substring(j + myOrder, j + (myOrder + 1)));
			}

		}
// Figured this out.
		String s = text.substring(text.length() - myOrder, text.length());
		if (MarkovMap.containsKey(s) == false) {
			MarkovMap.put(s, new ArrayList<String>());
			MarkovMap.get(s).add(PSEUDO_EOS);
		}
		else if (MarkovMap.containsKey(s)) {
			MarkovMap.get(s).add(PSEUDO_EOS);
		}

	}

	public int size() {
		return myText.length();
	}

	public String getRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - myOrder);

		String current = myText.substring(index, index + myOrder);
		// System.out.printf("first random %d for '%s'\n",index,current);
		sb.append(current);
		for (int k = 0; k < length - myOrder; k++) {
			ArrayList<String> follows = getFollows(current);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());

			String nextItem = follows.get(index);
			if (nextItem.equals(PSEUDO_EOS)) {
				// System.out.println("PSEUDO");
				break;
			}
			sb.append(nextItem);
			current = current.substring(1) + nextItem;
		}
		return sb.toString();
	}

	public ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = MarkovMap.get(key);

		return follows;
	}

	@Override
	public int getOrder() {
		return myOrder;
	}

}
