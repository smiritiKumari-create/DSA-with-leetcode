import java.util.Arrays;

public class Solution {
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        
        int currentMax = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] > currentMax) {
                currentMax = nums[i];
            }
            prefixGcd[i] = gcd(nums[i], currentMax);
        }
        
        Arrays.sort(prefixGcd);
        
        int left = 0;
        int right = n - 1;
        long totalGcdSum = 0;
        
        while (left < right) {
            totalGcdSum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return totalGcdSum;
    }
}

