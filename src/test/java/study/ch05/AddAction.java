package study.ch05;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.math.BigDecimal;

import net.jqwik.api.stateful.Action;

public class AddAction implements Action<Basket> {

	private final Product product;
	private final int qty;

	public AddAction(Product product, int qty) {
		this.product = product;
		this.qty = qty;
	}

	@Override
	public Basket run(Basket basket) {

		BigDecimal currentValue = basket.getTotalValue();

		basket.add(product, qty);

		BigDecimal newProductValue = product.getPrice().multiply(valueOf(qty));
		BigDecimal newValue = currentValue.add(newProductValue);

		assertThat(basket.getTotalValue()).isEqualByComparingTo(newValue);

		return basket;
	}
}
