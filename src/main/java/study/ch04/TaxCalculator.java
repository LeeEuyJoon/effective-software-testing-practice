package study.ch04;

public class TaxCalculator {
	/**
	 * 사전/사후 조건을 명확하게 기술하는 것 역시 중요하고 권장됨
	 *
	 * @param value 세금 계산을 위한 기본값. 값은 양수여야 함
	 * @return 계산한 세금. 섹므은 항상 양수여야 함
	 */
	public double calculateTax(double value) {

		// if (value < 0) {
		// 	throw new RuntimeException("Value cannot be negative.");
		// }

		// 자바에서 assert 키워드로 구현 가능한 단언문으로 변경
		assert value >= 0 : "Value cannot be negative";

		double taxValue = 0;

		// 비즈니스 로직

		// if (taxValue < 0) {
		// 	throw new RuntimeException("Calculated tax value cannot be negative.");
		// }

		// 자바에서 assert 키워드로 구현 가능한 단언문으로 변경
		assert taxValue >=0 : "Calculated tax value cannot be negative";

		return taxValue;
	}
}
