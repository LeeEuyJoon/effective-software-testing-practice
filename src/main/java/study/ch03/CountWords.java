package study.ch03;

import static java.lang.Character.*;

public class CountWords {
	public int count(String str) {
		int words = 0;
		char last = ' ';

		for (int i = 0; i < str.length(); i++) {

			if (!isLetter(str.charAt(i)) &&
				(last == 's' || last == 'r')) {
				words++;
			}

			last = str.charAt(i);
		}

		if (last == 'r' || last == 's') {
			words++;
		}

		return words;
	}
}
