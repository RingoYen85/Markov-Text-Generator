import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

public class EfficientWordMarkov implements MarkovInterface<WordGram> {

	private String myText;
	private Random myRandom;
	private int myOrder;
	// calling an instance variable.
	private HashMap<WordGram, ArrayList<String>> markovWordMap;

	private static String PSEUDO_EOS = "";
	private static int RANDOM_SEED = 1234;

	public EfficientWordMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}

	public EfficientWordMarkov() {
		this(3);
	}

	public void setTraining(String text) {
		myText = text;
		markovWordMap = new HashMap<>();

		String[] textCut = myText.split("\\s+");

		for (int j = 0; j < textCut.length - myOrder; j++) {

			WordGram wG1 = new WordGram(textCut, j, myOrder);
			String s = textCut[j + myOrder];

			if (markovWordMap.containsKey(wG1) == false) {
				markovWordMap.put(wG1, new ArrayList<String>());
				markovWordMap.get(wG1).add(s);

			}

			else if (markovWordMap.containsKey(wG1)) {
				markovWordMap.get(wG1).add(s);
			}

		}

		// For the last term

		WordGram wG2 = new WordGram(textCut, textCut.length - myOrder, myOrder);

		if (markovWordMap.containsKey(wG2) == false) {
			markovWordMap.put(wG2, new ArrayList<String>());
			markovWordMap.get(wG2).add(PSEUDO_EOS);
		}

		else if (markovWordMap.containsKey(wG2)) {
			markovWordMap.get(wG2).add(PSEUDO_EOS);

		}
	}

	public int size() {
		return myText.length();
	}

	public String getRandomText(int numWords) {
		StringBuilder sb = new StringBuilder();

		String[] textCut = myText.split("\\s+");
		int index = myRandom.nextInt(textCut.length - myOrder);

		WordGram current = new WordGram(textCut, index, myOrder);
		// System.out.printf("first random %d for '%s'\n",index,current);
		sb.append(current);
		for (int k = 0; k < numWords - myOrder; k++) {
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

			sb.append(nextItem + " ");
			current = current.shiftAdd(nextItem);
		}
		return sb.toString();
	}

	public ArrayList<String> getFollows(WordGram Key) {
		ArrayList<String> follows = markovWordMap.get(Key);

		return follows;
	}

	@Override
	public int getOrder() {
		return myOrder;
	}

}
