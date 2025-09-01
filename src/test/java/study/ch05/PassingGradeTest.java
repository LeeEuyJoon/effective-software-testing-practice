package study.ch05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.FloatRange;

class PassingGradeTest {

	private final PassingGrade pg = new PassingGrade();

	/**
	 * fail 속성에 대한 속성 기반 테스트
	 */
	@Property
	void fail (
		@ForAll
		@FloatRange(min = 1f, max = 5f, maxIncluded = false)
		float grade
	) {
		assertFalse(pg.passed(grade));
	}

	/**
	 * pass 속성에 대한 속성 기반 테스트
	 */
	@Property
	void pass(
		@ForAll
		@FloatRange(min = 5f, max = 10f, maxIncluded = true)
		float grade) {
		assertTrue(pg.passed(grade));
	}

	/**
	 * invalidGrades 속성을 위한 속성 기반 테스트
	 */
	@Property
	void invalid(@ForAll("invalidGrades") float grade) {
		assertThrows(
			IllegalArgumentException.class,
			() -> pg.passed(grade)
		);
	}

	@Provide
	private Arbitrary<Float> invalidGrades() {
		return Arbitraries.oneOf(
			Arbitraries.floats().lessThan(1f),
			Arbitraries.floats().greaterThan(10f)
		);
	}
}