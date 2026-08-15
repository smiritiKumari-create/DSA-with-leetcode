class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXOR = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            totalXOR ^= num;
            if (num > 0) {
                hasNonZero = true;
            }
        }

        if (totalXOR != 0) {
            return nums.length;
        }
        
        return hasNonZero ? nums.length - 1 : 0;
    }
}
