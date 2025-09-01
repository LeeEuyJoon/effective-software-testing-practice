package study.ch05;

public class ArrayUtils {
	/**
	 * Null 배열을 받으면 -1 반환
	 * startIndex가음수이면 0으로 가정
	 * 찾고자 하는 값을 찾으면 인덱스 반환
	 * 배열에 찾고자 하는 값이 없으면 -1 반환
	 */
	public static int indexOf(final int[] array, final int valueToFind, int startIndex) {
		if (array == null) {
			return -1;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i < array.length; i++) {
			if (valueToFind == array[i]) {
				return i;
			}
		}
		return -1;
	}
}
