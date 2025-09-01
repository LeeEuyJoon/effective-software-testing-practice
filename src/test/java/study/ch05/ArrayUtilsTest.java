package study.ch05;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

class ArrayUtilsTest {

	private ArrayUtils au = new ArrayUtils();

	/**
	 * indexOf에 대한 파라미터화 테스트
	 */
	@ParameterizedTest
	@MethodSource("testCases")
	void testIndexOf(int[] array, int valueToFind, int startIndex, int expectedResult) {
		int result = ArrayUtils.indexOf(array, valueToFind, startIndex);
		assertThat(result).isEqualTo(expectedResult);
	}

	static Stream<Arguments> testCases() {
		int[] array = new int[] {1, 2, 3, 4, 5, 4, 6, 7 };

		return Stream.of(
			// array가 null인 경우
			of(null, 1, 1, -1),

			// array의 요소가 하나이고, array에 valueToFind가 있는 경우
			of(new int[] { 1 }, 1, 0, 0),
			// array의 요소가 하나이고, valueToFind가 없는 경우
			of(new int[] { 1 }, 2, 0, -1),

			// startIndex가 음수이고, array에 값이 있는 경우
			of(array, 1, 10, -1),
			// startIndex가 array의 범위 밖에 있는 경우
			of(array, 2, -1, 1),
			// array의 요소가 다수이고, array에 valueToFind가 있으며, startIndex가 valueToFind 이후인 경우
			of(array, 4, 6, -1),
			// array의 요소가 다수이고, array에 valueToFind가 있으며, startIndex가 valueToFind의 이전인 경우
			of(array, 4, 1, 3),
			// array의 요소가 다수이고, array에 valueToFind가 있으며, startIndex가 valueToFind의 정확한 위치인 경우
			of(array, 4, 3, 3),
			// array의 요소가 다수이고, array에 valueToFind가 여러 개 있으며, startIndex가 valueToFind 이전인 경우
			of(array, 4, 1, 3),
			// array의 요소가 다수이고, array에 valueToFind가 여러 개 있으며, 그중 하나가 startIndex 이전에 있는 경우
			of(array, 4, 4, 5),
			// array의 요소가 다수이고, array에 valueToFind가 없는 경우
			of(array, 8, 0, -1)


			// 테스트 매개변수를 개발자가 다 하나하나 직접 작성하는데 ... 이건 속성 기반 테스트가 아니라 예시 기반 테스트 아닌가?
			// 맞네 이건 예시 기반 테스트이고 다음에 이걸 속성 기반 테스트로 하는법이 나온다...
		);
	}

	@Property
	void indexOfShouldFindFirstValue(
		@ForAll
		@Size(value = 100)List<@IntRange(min = -1000, max = 1000) Integer> numbers,
		@ForAll
		@IntRange(min = 1001, max = 2000) int value,
		@ForAll
		@IntRange(max = 99) int indexToAddElement,
		@ForAll
		@IntRange(max = 99) int startIndex) {

		numbers.add(indexToAddElement, value);

		int[] array = convertListToArray(numbers);

		int expectedIndex = indexToAddElement >= startIndex ? indexToAddElement : -1;

		assertThat(ArrayUtils.indexOf(array, value, startIndex)).isEqualTo(expectedIndex);

	}

	private int[] convertListToArray(List<Integer> numbers) {
		int[] array = numbers.stream().mapToInt(x -> x).toArray();
		return array;
	}
}