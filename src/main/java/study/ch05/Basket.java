package study.ch05;

import static java.math.BigDecimal.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Basket {
	private BigDecimal totalValue = BigDecimal.ZERO;
	private Map<Product, Integer> basket = new HashMap<>();

	public void add(Product product, int qtyToAdd) {
		// 모든 사전 조건 검사
		assert product != null : "Product is required";
		assert qtyToAdd > 0 : "Quantity has to be greater than zero";

		// 나중에 사후 조건에서 사용할 이전 값 저장
		BigDecimal oldTotalValue = totalValue;

		int existingQuantity = basket.getOrDefault(product, 0); // 제품이 이미 장바구니에 있는지 확인
		int newQuantity = existingQuantity + qtyToAdd;
		basket.put(product, newQuantity);

		// 제품의 이전 합계와 새로운 수량을 감안하여 최종 합계 계산
		BigDecimal valueAlreadyInTheCart = product.getPrice().multiply(valueOf(existingQuantity));
		BigDecimal newFinalValueForTheProduct = product.getPrice().multiply(valueOf(newQuantity));

		// 장바구니에 있는 제품 가격의 합계에서 이전 합계를 빼고 새로운 제품 가격으 ㅣ합계 더함
		totalValue = totalValue
			.subtract(valueAlreadyInTheCart)
			.add(newFinalValueForTheProduct);

		// 사후 조건 및 불변식 검사
		assert basket.containsKey(product) : "Product was not inserted in the basket";
		assert totalValue.compareTo(oldTotalValue) == 1 : "Total value should be greater than previous total value";
		assert invariant() : "Invariant does not hold";
	}

	public void remove(Product product) {
		// 사전 조건 검사
		assert product != null : "product can't be null";
		assert basket.containsKey(product) : "Product must already be in the basket";

		int qty = basket.get(product);

		// 장바구니 전체 가격에서 차감할 가격 계산
		totalValue = totalValue.subtract(product.getPrice().multiply(valueOf(qty)));

		// 맵에서 제품 제거
		basket.remove(product);

		// 사후 조건과 불변식 검사
		assert !basket.containsKey(product) : "Product is still in the basket";
		assert invariant() : "Invariant does not hold";
	}

	private boolean invariant() {
		return totalValue.compareTo(BigDecimal.ZERO) >= 0;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	// 물건이 장바구니에 있으면 수량을 반환
	public int quantityOf(Product product) {
		assert basket.containsKey(product);
		return basket.get(product);
	}

	// 제품 집합을 복제해서 반환 (원본 아님)
	public Set<Product> products() {
		return Collections.unmodifiableSet(basket.keySet());
	}

	@Override
	public String toString() {
		return "BasketCase{" +
			"totalValue=" + totalValue +
			", basket=" + basket +
			'}';
	}
}
