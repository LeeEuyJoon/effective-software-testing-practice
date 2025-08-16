package study.ch03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountWordsTest {

	/**
	 * 불완전한 테스트 1
	 */
	@Test
	void twoWordsEndingWithS() {
		int words = new CountWords().count("dogs cats");
		assertTrue(words == 2);
	}

	/**
	 * 불완전한 테스트 2
	 */
	@Test
	void noWordsAtAll() {
		int words = new CountWords().count("dog cat");
		assertTrue(words == 0);
	}

	@Test
	void wordsThatEndInR() {
		int words = new CountWords().count("car bar");
		assertTrue(words == 2);
	}


}