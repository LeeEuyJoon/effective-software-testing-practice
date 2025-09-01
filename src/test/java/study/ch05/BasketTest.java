package study.ch05;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.stateful.ActionSequence;

class BasketTest {

	private Basket basket = new Basket();

	/**
	 * 체계적이지 못한 테스트 1 : 장바구니에 물건 추가
	 */
	@Test
	void addProducts() {
		basket.add(new Product("TV", valueOf(10)), 2);
		basket.add(new Product("Playstation", valueOf(100)), 1);

		assertThat(basket.getTotalValue()).isEqualByComparingTo(valueOf(10*2 + 100*1));
	}

	/**
	 * 체계적이지 못한 테스트 2 : 같은 물건을 두 번 추가하면 수량만큼 합산
	 */
	@Test
	void addSameProductTwice() {
		Product p = new Product("TV", valueOf(10));
		basket.add(p, 2);
		basket.add(p, 3);

		assertThat(basket.getTotalValue()).isEqualTo(valueOf(10*5));
	}

	/**
	 * 체계적이지 못한 테스트 3 : 장바구니에서 물건을 제거하는 테스트
	 */
	@Test
	void removeProducts() {
		basket.add(new Product("TV", valueOf(100)), 1);

		Product p = new Product("Playstation", valueOf(10));
		basket.add(p, 2);
		basket.remove(p);

		assertThat(basket.getTotalValue()).isEqualByComparingTo(valueOf(100));
	}


	private Arbitrary<AddAction> addAction() {
		Arbitrary<Product> products = Arbitraries.oneOf(
			randomProducts
				.stream()
				.map(product -> Arbitraries.of(product))
				.collect(Collectors.toList()));

			Arbitrary<Integer> qtys =
				Arbitraries.integers().between(1, 100);

			return Combinators
				.combine(products, qtys)
				.as((product, qty) -> new AddAction(product, qty));
	}

	static List<Product> randomProducts = new ArrayList<>() {{
	add(new Product("TV", new BigDecimal("100")));
	add(new Product("PlayStation", new BigDecimal("150.3")));
	add(new Product("Refrigerator", new BigDecimal("180.27")));
	add(new Product("Soda", new BigDecimal("2.69")));
	}};

	private Arbitrary<RemoveAction> removeAction() {
		return Arbitraries.of(new RemoveAction());
	}

	@Provide
	Arbitrary<ActionSequence<Basket>> addsAndRemoves() {
		return Arbitraries.sequences(Arbitraries.oneOf(
			addAction(),
			removeAction()
		));
	}

	/**
	 * 드디어 옳게 된 테스트
	 * 추가, 제거 동작을 수행하는 속성 기반 테스트
	 */
	@Property
	void sequenceOfAddsAndRemoves(
		@ForAll("addsAndRemoves")
		ActionSequence<Basket> actions) {
		actions.run(new Basket());
	}
}