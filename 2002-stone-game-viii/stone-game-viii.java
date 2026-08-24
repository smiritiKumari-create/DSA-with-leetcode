class Solution {

    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Build prefix sums
        int[] prefix = new int[n];

        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // Taking all stones ends the game immediately
        int diff = prefix[n - 1];

        // Try every possible earlier merge
        for (int i = n - 2; i >= 1; i--) {
            diff = Math.max(diff, prefix[i] - diff);
        }

        return diff;
    }
}