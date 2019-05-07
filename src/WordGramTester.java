import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class WordGramTester {

	private WordGram[] myGrams;

	@Before
	public void setUp() {
		String str = "aa bb cc aa bb cc aa bb cc aa bb dd ee ff gg hh ii jj";
		String[] array = str.split("\\s+");
		myGrams = new WordGram[array.length - 2];
		for (int k = 0; k < array.length - 2; k++) {
			myGrams[k] = new WordGram(array, k, 3);
		}
	}

	@Test
	public void testHashEquals() {
		assertEquals("hash fail on equals 0,3", myGrams[0].hashCode(), myGrams[3].hashCode());
		assertEquals("hash fail on equals 0,3", myGrams[0].hashCode(), myGrams[6].hashCode());
		assertEquals("hash fail on equals 0,3", myGrams[1].hashCode(), myGrams[4].hashCode());
		assertEquals("hash fail on equals 0,3", myGrams[2].hashCode(), myGrams[8].hashCode());
		assertEquals("hash fail on equals 0,3", myGrams[2].hashCode(), myGrams[5].hashCode());
	}

	@Test
	public void testEquals() {

		assertEquals("eq fail on 0,3", myGrams[0].equals(myGrams[3]), true);
		assertEquals("eq fail on 0,6", myGrams[0].equals(myGrams[6]), true);
		assertEquals("eq fail on 1,4", myGrams[1].equals(myGrams[4]), true);
		assertEquals("eq fail on 2,5", myGrams[2].equals(myGrams[5]), true);
		assertEquals("eq fail on 2,8", myGrams[2].equals(myGrams[8]), true);
		assertEquals("eq fail on 0,2", myGrams[0].equals(myGrams[2]), false);
		assertEquals("eq fail on 0,4", myGrams[0].equals(myGrams[2]), false);
		assertEquals("eq fail on 2,3", myGrams[2].equals(myGrams[3]), false);
		assertEquals("eq fail no 2,6", myGrams[2].equals(myGrams[6]), false);
		assertEquals("eq fail no 7,8", myGrams[7].equals(myGrams[8]), false);
	}

	@Test
	public void testHash() {
		Set<Integer> set = new HashSet<Integer>();
		for (WordGram w : myGrams) {
			set.add(w.hashCode());
		}

		assertTrue("hash code test", set.size() > 9);
	}

	@Test
	public void testCompare() {
		String[] words = { "apple", "zebra", "mongoose", "hat" };
		WordGram a = new WordGram(words, 0, 4);
		WordGram b = new WordGram(words, 0, 4);
		WordGram a2 = new WordGram(words, 0, 3);
		WordGram b2 = new WordGram(words, 2, 0);

		assertEquals("comp fail self", a.compareTo(a) == 0, true);
		assertEquals("comp fail copy", a.compareTo(b) == 0, true);
		assertEquals("fail sub", a2.compareTo(a) < 0, true);
		assertEquals("fail super", a.compareTo(a2) > 0, true);
		assertEquals("fail empty", b2.compareTo(a2) < 0, true);
	}

	@Test
	public void testToString() {

		String[] sampleWords = { "cat", "dog", "mouse", "horse" };
		WordGram A = new WordGram(sampleWords, 0, 2);
		WordGram B = new WordGram(sampleWords, 0, 3);
		WordGram C = new WordGram(sampleWords, 1, 3);
		WordGram D = new WordGram(sampleWords, 2, 2);

		String actualA = "{cat,dog}";
		String actualB = "{cat,dog,mouse}";
		String actualC = "{dog,mouse,horse}";
		String actualD = "{mouse,horse}";

		assertEquals("String fails", A.toString().equals(actualA), true);
		assertEquals("String fails", B.toString().equals(actualB), true);
		assertEquals("String fails", C.toString().equals(actualC), true);
		assertEquals("String fails", D.toString().equals(actualD), true);

	}

	@Test
	public void testShiftAdd() {
		

		String[] someWords = { "cheese", "animal", "rabbit", "possum" };
		String last = "cherry";

		String[] newE = { "animal", "rabbit", "possum", "cherry" };
		WordGram old_e = new WordGram(someWords, 0, someWords.length);
		WordGram new_e = new WordGram(newE, 0, newE.length);
		
		String [] oneWord = {"horse"};
		String [] newF = {"cherry"};
		WordGram old_f = new WordGram(oneWord,0,oneWord.length);
		WordGram new_f = new WordGram(newF,0,oneWord.length);
		
		String [] twoWords = {"banana","orange"};
		String [] newG = {"orange","cherry"};
		WordGram old_G = new WordGram(twoWords,0,twoWords.length);
		WordGram new_G = new WordGram(newG, 0, twoWords.length);
		
		
		assertEquals("Method fails", old_e.shiftAdd(last).equals(new_e), true);
		assertFalse("Method fails", old_e.shiftAdd(last).equals(old_e));
		assertEquals("Method fails", old_f.shiftAdd(last).equals(new_f),true);
		assertEquals("Method fails", old_G.shiftAdd(last).equals(new_G),true);
		
		
		
		

	}

}
