package study.ch03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class LinkedListRemoveTest {

	/**
	 * 112p 문제 3.2
	 * LinkedList의 remove(Object o) 메서드에 대해 코드 줄 커버리지 100% 달성하는 테스트 스위트 작성해보기
	 * T1: LinkedList에 null인 노드가 있고, null인 Object를 remove 하는 경우
	 * T2: remove 하려는 Object가 null이 아니면서 LinkedList에 존재하는 요소일 경우
	 * T3: remove 하려는 Object가 null이 아니면서 LinkedList에 없는 요소일 경우
	 * 세 개만 해도 코드 줄은 커버리지 100% 인듯 ...?
	 */
	@Test
	void removeTest() {
		LinkedList<String> lst = new LinkedList<>(Arrays.asList("A", "B", "C", null));

		assertTrue(lst.remove(null));
		assertTrue(lst.remove("B"));
		assertTrue(lst.remove("D") == false);

		lst.remove();
	}


}
