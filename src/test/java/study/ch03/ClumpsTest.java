package study.ch03;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ClumpsTest {

	/**
	 * T1: 빈 배열인 경우
	 * T2: 배열이 null인 경우
	 * T3: 중간에 세 개의 요소가 한 덩어리를 이루는 배열인 경우
	 * T4: 요소가 하나만 있는 배열
	 */
	@ParameterizedTest
	@MethodSource("generator")
	void testClumps(int[] nums, int expectedNoOfClumps) {
		assertTrue(Clumps.countClumps(nums) == (expectedNoOfClumps));
	}

	static Stream<Arguments> generator() {
		return Stream.of(
			of(new int[]{}, 0), // T1
			of(null, 0), // T2
			of(new int[]{1, 2, 2, 2, 1}, 1), // T3
			of(new int[]{1}, 0) // T4
		);
	}

}