package study.ch03;

public class Clumps {

	/**
	 * null이거나 빈 문자열이면 0 반환
	 * 현재 숫자가 이전 숫자와 같으면 덩어리로 판단
	 * 현재 숫자가 이전 숫자와 다르면 덩어리가 아님
	 */
	public static int countClumps(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int count = 0;
		int prev= nums[0];
		boolean inClump = false;
		for (int i = 1; i < nums.length; i++) {
			if(nums[i] == prev && !inClump) {
				inClump = true;
				count += 1;
			}
			if (nums[i] != prev) {
				prev = nums[i];
				inClump = false;
			}
		}
		return count;
	}
}
