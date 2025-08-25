package study.ch04;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {
	private BigDecimal totalValue = BigDecimal.ZERO;
	private Map<Object, Integer> basket = new HashMap<>();
	BigDecimal oldTotalValue = totalValue;

	/**
	 * 사전 조건
	 * 1. 제품이 널이 아님을 보장하는 사전 조건
	 * 2. 추가할 수량이 0보다 큼을 보장하는 사전 조건
	 * 3. 제품이 장바구니에 추가되었음을 보장하는 사후 조건
	 *
	 * 사후 조건
	 * 1. 사후 조건을 위해 이전 합계 저장
	 * 2. 합계가 이전 합계보다 크다는 것을 보장하는 사후 조건
	 */
	public void add(Object product, int qtyToAdd) {

		assert product != null : "Product is required";
		assert qtyToAdd > 0 : "Quantity has to be greater than zero";

		// 제품 추가 로직
		// 합계 갱신 로직

		assert basket.containsKey(product) : "Product was not inserted in the basket";

		assert totalValue.compareTo(oldTotalValue) == 1:
			"Total value should be greater than previous total value";

		assert invariant() : "Invariant does not hold";
	}

	/**
	 * 사전 조건 : 제품이 널이면 안된다. 또한 장바구니에 있어야 한다.
	 * 사후 존건 : 제품이 더 잇아 장바구니에 있으면 안 된다.
	 */
	public void remove(Object product) {

		assert product != null : "product can't be null";
		assert basket.containsKey(product) : "Product must already be in the basket";

		// 제품 삭제 로직
		// 합계 갱신 로직

		assert !basket.containsKey(product) : "Product is still in the basket";

		assert invariant() : "Invariant does not hold";
	}

	private boolean invariant() {
		return totalValue.compareTo(BigDecimal.ZERO) >= 0;
	}
}
