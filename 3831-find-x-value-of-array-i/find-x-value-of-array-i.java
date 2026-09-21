import java.util.*;

class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];

            int value = num % k;

            // Subarray containing only nums[i]
            next[value]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                int newR = (r * value) % k;
                next[newR] += dp[r];
            }

            // Add counts
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}