package study.ch02;

import static org.junit.jupiter.api.Assertions.*;
import static study.ch02.SubstringBetween.*;

import org.junit.jupiter.api.Test;

class SubstringBetweenTest {


	@Test
	void simpleCase() {
		assertArrayEquals(substringBetween("abcd", "a", "d"), new String[] {"bc"});
	}

	@Test
	void manySubstrings() {
		assertArrayEquals(substringBetween("abcdabcdab", "a", "d"), new String[] {"bc", "bc"});
	}

	@Test
	void openAndCloseTagsThatAreLongerThan1Char() {
		assertArrayEquals(substringBetween("aabcddaabfddaab", "aa", "dd"), new String[] {"bc", "bf"});
	}

	/**
	 * T1: str이 null인 경우
	 * T2: str이 빈 문자열인 경우
	 */
	@Test
	void strIsNullOrEmpty() {
		assertArrayEquals(substringBetween(null, "a", "b"), null);
		assertArrayEquals(substringBetween("", "a", "b"), new String[] {});
	}

	/**
	 * T3: open이 null인 경우
	 * T4: open이 빈 문자열인 경우
	 */
	@Test
	void openIsNullOrEmpty() {
		assertArrayEquals(substringBetween("abc", null, "b"), null);
		assertArrayEquals(substringBetween("abc", "", "b"), null);
	}

	/**
	 * T5: close가 null인 경우
	 * T6: close가 빈 문자열인 경우
	 */
	@Test
	void closeIsNullOrEmpty() {
		assertArrayEquals(substringBetween("abc", "a", null), null);
		assertArrayEquals(substringBetween("abc", "a", ""), null);
	}

	/**
	 * T7: str에 문자가 하나밖에 없고 open 태그와 일치하는 경우
	 * T8: str에 문자가 하나밖에 없고 close 태그와 일치하는 경우
	 * T9: str에 문자가 하나밖에 없고 open 태그, clsoe 태그 모두 일치하지 않는 경우
	 * T10: str에 문자가 하나밖에 없고 open 태그, close 태그 모두와 일치하는 경우
	 */
	@Test
	void strOfLength1() {
		assertArrayEquals(substringBetween("a", "a", "b"), null);
		assertArrayEquals(substringBetween("a", "b", "a"), null);
		assertArrayEquals(substringBetween("a", "b", "b"), null);
		assertArrayEquals(substringBetween("a", "a", "a"), null);
	}

	/**
	 * T11: str이 open 태그와 close 태그를 모두 포함하지 않는 경우
	 * T12: str이 open 태그는 포함하지만, close 태그를 포함하지 않는 경우
	 * T13: str이 close 태그는 포함하지만, open 태그를 포함하지 않는 경우
	 * T14: str이 open 태그와 close 태그를 모두 포함하는 경우
	 * T15: str이 open 태그와 close 태그 모두를 여러 번 포함하는 경우
	 * + 공백 문자열 검증 추가
	 */
	@Test
	void openAndCloseOfLength1() {
		assertArrayEquals(substringBetween("abc", "x", "y"), null);
		assertArrayEquals(substringBetween("abc", "a", "y"), null);
		assertArrayEquals(substringBetween("abc", "x", "c"), null);
		assertArrayEquals(substringBetween("abc", "a", "c"), new String[] {"b"});
		assertArrayEquals(substringBetween("abcabc", "a", "c"), new String[] {"b", "b"});
		assertArrayEquals(substringBetween("abcabyt byrc", "a", "c"), new String[] {"b", "byt byr"});
	}

	/**
	 * T16: str이 open 태그와 close 태그를 모두 포함하지 않는 경우
	 * T17: open 태그는 포함하지만, close 태그를 포함하지 않는 경우
	 * T18: str이 close 태그는 포함하지만, open 태그를 초함하지 않는 경우
	 * T19: str이 open 태그와 close 태그를 모두 포함하는 경우
	 * T20: open 태그와 close 태그 모두를 여러 번 포함하는 경우
	 * + 공백 문자열 검증 추가
	 */
	@Test
	void openAndCloseTagsOfDifferentSizes() {
		assertArrayEquals(substringBetween("aabcc", "xx", "yy"), null);
		assertArrayEquals(substringBetween("aabcc", "aa", "yy"), null);
		assertArrayEquals(substringBetween("aabcc", "xx", "cc"), null);
		assertArrayEquals(substringBetween("aabbcc", "aa", "cc"), new String[] {"bb"});
		assertArrayEquals(substringBetween("aabbccaaeecc", "aa", "cc"), new String[] {"bb", "ee"});
		assertArrayEquals(substringBetween("a abb ddc ca abbcc", "a a", "c c"), new String[] {"bb dd"});
	}

	/**
	 * T21: str이 open 태그와 close 태그를 모두 포함하지만 그 사이에 문자가 없는 경우
	 */
	@Test
	void noSubstringBetweenOpenAndCloseTags() {
		assertArrayEquals(substringBetween("aabb", "aa", "bb"), new String[] {""});
	}

}