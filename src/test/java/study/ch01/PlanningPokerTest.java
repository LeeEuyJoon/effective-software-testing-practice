package study.ch01;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.Property;
import net.jqwik.api.ForAll;
import net.jqwik.api.Provide;


import org.junit.jupiter.api.Test;

class PlanningPokerTest {
	// Null 목록에 대한 테스트 케이스
	@Test
	void rejectNullInput() {
		assertThrows(
			IllegalArgumentException.class,
			() -> new PlanningPoker().identifyExtremes(null)
		);
	}

	// 빈 목록에 대한 테스트 케이스
	@Test
	void rejectEmptyList() {
		assertThrows(
			IllegalArgumentException.class,
			() -> {
				List<Estimate> emptyList = Collections.emptyList();
				new PlanningPoker().identifyExtremes(emptyList);
			}
		);
	}

	// 요소가 하나인 목록에 대한 테스트 케이스
	@Test
	void rejectSingleEstimate() {
		assertThrows(
			IllegalArgumentException.class,
			() -> {
				List<Estimate> list = Arrays.asList(new Estimate("Eleanor", 1));
				new PlanningPoker().identifyExtremes(list);
			}
		);
	}

	// 두 요소를 지닌 목록을 위한 테스트 케이스
	@Test
	void twoEstimates() {
		List<Estimate> list = Arrays.asList(
			new Estimate("Mauricio", 10),
			new Estimate("Frank", 5)
		);
		List<String> devs = new PlanningPoker().identifyExtremes(list);

		assertTrue(devs.containsAll(Arrays.asList("Mauricio", "Frank")));
	}

	// 많은 요소를 지닌 목록을 위한 테스트 케이스
	@Test
	void manyEstimates() {
		List<Estimate> list = Arrays.asList(
			new Estimate("Mauricio", 10),
			new Estimate("Arie", 5),
			new Estimate("Frank", 7)
		);

		List<String> devs = new PlanningPoker().identifyExtremes(list);

		assertTrue(devs.containsAll(Arrays.asList("Mauricio", "Arie")));
	}

	// 다양한 추정을 테스트하는 속성 기반 테스트
	@Property
	void inAnyOrder(@ForAll("estimates") List<Estimate> estimates) {

		estimates.add(new Estimate("MrLowEstimate", 1));
		estimates.add(new Estimate("MsHighEstimate", 100));

		Collections.shuffle(estimates);

		List<String> devs = new PlanningPoker().identifyExtremes(estimates);

		assertTrue(devs.containsAll(Arrays.asList("MrLowEstimate", "MsHighEstimate")));
	}

	// 임의의 값을 생성하는 Provide
	@Provide
	Arbitrary<List<Estimate>> estimates() {

		Arbitrary<String> names = Arbitraries.strings().withCharRange('a', 'z').ofLength(5);

		Arbitrary<Integer> values = Arbitraries.integers().between(2, 99);

		Arbitrary<Estimate> estimates = Combinators.combine(names, values)
			.as((name, value) -> new Estimate(name, value));

		return estimates.list().ofMinSize(1);
	}

	// 처음 등장한 중복 개발자를 반환하는지 확인하는 테스트
	@Test
	void developersWithSameEstimates() {
		List<Estimate> list = Arrays.asList(
			new Estimate("Mauricio", 10),
			new Estimate("Arie", 5),
			new Estimate("Andy", 10),
			new Estimate("Frank", 7),
			new Estimate("Annibale", 5)
		);
		List<String> devs = new PlanningPoker().identifyExtremes(list);

		assertTrue(devs.containsAll(Arrays.asList("Mauricio", "Arie")));
	}

	// 추정이 모두 같은 경우 빈 목록 반환 테스트
	@Test
	void allDevelopersWithTheSameEstimate() {
		List<Estimate> list = Arrays.asList(
			new Estimate("Mauricio", 10),
			new Estimate("Arie", 10),
			new Estimate("Andy", 10),
			new Estimate("Frank", 10),
			new Estimate("Annibale", 10)
		);
		List<String> devs = new PlanningPoker().identifyExtremes(list);

		assertTrue(devs.isEmpty());
	}

}