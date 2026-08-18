import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k = 1 -> Find the largest unique element
        if (k == 1) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (int x : nums) {
                counts.put(x, counts.getOrDefault(x, 0) + 1);
            }
            int ans = -1;
            for (int x : nums) {
                if (counts.get(x) == 1) {
                    ans = Math.max(ans, x);
                }
            }
            return ans;
        }

        // Case 2: k = n -> Only 1 subarray exists, return global maximum
        if (k == n) {
            int max = -1;
            for (int x : nums) {
                max = Math.max(max, x);
            }
            return max;
        }

        // Case 3: 1 < k < n -> Only boundaries nums[0] or nums[n-1] can be valid
        int count0 = 0, countN = 0;
        for (int x : nums) {
            if (x == nums[0]) count0++;
            if (x == nums[n - 1]) countN++;
        }

        int ans = -1;
        if (count0 == 1) ans = Math.max(ans, nums[0]);
        if (countN == 1) ans = Math.max(ans, nums[n - 1]);

        return ans;
    }
}
