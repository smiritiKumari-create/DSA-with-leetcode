import java.util.*;

class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store [value, original index]
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by value
        Arrays.sort(arr, (a, b) ->
            Integer.compare(a[0], b[0])
        );

        int start = 0;

        while (start < n) {

            int end = start;

            // Find the complete connected group
            while (end + 1 < n &&
                   (long) arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            // Collect original indices of this group
            int[] indices = new int[end - start + 1];

            for (int i = start; i <= end; i++) {
                indices[i - start] = arr[i][1];
            }

            // Smallest original indices first
            Arrays.sort(indices);

            // Smallest values → smallest indices
            for (int i = 0; i < indices.length; i++) {
                nums[indices[i]] = arr[start + i][0];
            }

            // Move to next group
            start = end + 1;
        }

        return nums;
    }
}