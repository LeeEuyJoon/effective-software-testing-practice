package study.ch02;

import java.util.ArrayList;
import java.util.List;

public class SubstringBetween {

	private static final String[] EMPTY_STRING_ARRAY = new String[0];

	public static String[] substringBetween(final String str, final String open, final String close) {
		if (str == null || open == null || close == null || open.isEmpty() || close.isEmpty()) {
			return null;
		}

		final int strLen = str.length();
		if (strLen == 0) {
			return EMPTY_STRING_ARRAY;
		}

		final int openLen = open.length();
		final int closeLen = close.length();
		final List<String> list = new ArrayList<>();
		int pos = 0;

		while (pos < strLen) {
			int start = str.indexOf(open, pos);
			if (start < 0) break;

			int from = start + openLen;
			int end = str.indexOf(close, from);
			if (end < 0) break;

			list.add(str.substring(from, end));
			pos = end + closeLen;
		}

		return list.isEmpty() ? null : list.toArray(EMPTY_STRING_ARRAY);
	}
}
