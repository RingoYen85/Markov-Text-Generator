import java.util.*;

public class WordGram implements Comparable<WordGram> {

	private String[] myWords;
	private int myHash;

	public WordGram(String[] source, int start, int size) {

		// Need to initialize the source and size.

		myWords = new String[size];
		myHash = 0;
		System.arraycopy(source, start, myWords, 0, size);

	}

	public int hashCode() {

		if (myHash == 0) {
			myHash = 11;
			for (int k = 0; k < myWords.length; k++) {
				myHash = myHash * 31 + myWords[k].hashCode() * (k + 1) * 7;
			}
			return myHash;

		} else {

			return myHash;

		}

	}

	public String toString() {

		String s = "{";

		for (int j = 0; j < myWords.length - 1; j++) {
			s = s.concat(myWords[j]);
			s = s.concat(",");

		}

		s = s.concat(myWords[myWords.length - 1]);
		s = s.concat("}");

		System.out.println(s);
		return s;

	}

	public boolean equals(Object other) {
		if (!(other instanceof WordGram)) {
			return false;
		}
		// cast to get access to the global variables
		WordGram wg = (WordGram) other;

		if (wg.myWords.length != this.myWords.length) {
			return false;
		}

		for (int k = 0; k < myWords.length; k++) {
			if (!(wg.myWords[k].equals(this.myWords[k]))) {
				return false;
			}

		}

		return true;
	}

	public int compareTo(WordGram other) {

		int len1 = this.myWords.length;
		int len2 = other.myWords.length;
		int lim = Math.min(len1, len2);

		for (int j = 0; j < lim; j++) {
			if (other.myWords[j].compareTo(this.myWords[j]) < 0) {
				return -1;

			} else if (other.myWords[j].compareTo(this.myWords[j]) > 0) {
				return 1;

			}

		}

		if (other.myWords.length > this.myWords.length) {
			return -1;
		}

		if (other.myWords.length < this.myWords.length) {
			return 1;
		}

		return 0;

	}

	public WordGram shiftAdd(String last) {
		String[] newWords = new String[myWords.length];
		System.arraycopy(myWords, 1, newWords, 0, myWords.length - 1);
		newWords[newWords.length - 1] = last;

		return new WordGram(newWords, 0, myWords.length);
	}

	public int length() {
		return myWords.length;
	}

}
