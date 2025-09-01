package study.ch05;

import static java.util.Comparator.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.List;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;


class UniqueMethodTest {

	private final UniqueMethod um = new UniqueMethod();

	@Property
	void unique(
		@ForAll
		@Size(value = 100)
		List<@IntRange(min = 1, max = 20) Integer>
		numbers) {

		int[] doubles = convertListToArray(numbers);
		int[] result = UniqueMethod.unique(doubles);

		/**
		 * 모든 요소 포함 확인
		 * 중복 없음 확인
		 * 내림차순 확인
		 */
		assertThat(result)
			.contains(doubles)
			.doesNotHaveDuplicates()
			.isSortedAccordingTo(reverseOrder());
	}

	private int[] convertListToArray(List<Integer> numbers) {
		int[] array = numbers
			.stream()
			.mapToInt(x -> x)
			.toArray();

		return array;
	}
}