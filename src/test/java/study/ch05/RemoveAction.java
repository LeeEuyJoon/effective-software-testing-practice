package study.ch05;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import net.jqwik.api.stateful.Action;


public class RemoveAction implements Action<Basket> {

	@Override
	public Basket run(Basket basket){
		BigDecimal currentValue = basket.getTotalValue();
		Set<Product> productsInBasket = basket.products();

		if(productsInBasket.isEmpty()){
			return basket;
		}

		Product randomProduct = pickRandom(productsInBasket);
		double currentProductQty = basket.quantityOf(randomProduct);
		basket.remove(randomProduct);

		BigDecimal basketValueWithoutRandomProduct = currentValue
			.subtract(randomProduct.getPrice().multiply(BigDecimal.valueOf(currentProductQty)));

		assertThat(basket.getTotalValue()).isEqualByComparingTo(basketValueWithoutRandomProduct);

		return basket;
	}

	private Product pickRandom(Set<Product> productSet){
		List<Product> productList = productSet.stream().toList();
		return productList.get((int)Math.floor(Math.random() * productList.size()));
	}
}