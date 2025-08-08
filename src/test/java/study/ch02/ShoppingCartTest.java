package study.ch02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShoppingCartTest {
	private final ShoppingCart cart = new ShoppingCart();

	@Test
	void noItems() {
		assertEquals(cart.totalPrice(), 0);
	}

	@Test
	void itemsInTheCart() {
		cart.add(new CartItem("TV", 1, 120));
		assertEquals(cart.totalPrice(), 120);

		cart.add(new CartItem("Chocolate", 2, 2.5));
		assertEquals(cart.totalPrice(), 120 + 2.5 * 2);
	}

}