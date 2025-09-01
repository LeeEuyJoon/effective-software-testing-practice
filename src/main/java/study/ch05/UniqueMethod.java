package study.ch05;

import java.util.Iterator;
import java.util.TreeSet;

public class UniqueMethod {

	/**
	 * 반복된 요소를 걸러내기 위해 TreeSet 이용
	 * 트리의 크기를 이용해서 새로운 배열 생성
	 * TreeSet을 방문하면서 새 배열에 요소 추가
	 */
	public static int[] unique(int[] data) {
		TreeSet<Integer> values = new TreeSet<Integer>();
		for (int i = 0; i < data.length; i++) {
			values.add(data[i]);
		}

		final int count = values.size();
		final int[] out = new int[count];

		Iterator<Integer> iterator = values.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			out[count - ++i] = iterator.next();
		}
		return out;
	}
}
