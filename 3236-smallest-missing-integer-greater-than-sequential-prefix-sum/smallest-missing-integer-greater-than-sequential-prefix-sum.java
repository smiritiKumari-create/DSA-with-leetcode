import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];
        
        // Find the longest sequential prefix sum
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        
        // Store all array elements in a hash set
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Find the smallest missing integer
        while (set.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}
