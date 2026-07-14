class Solution {
    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        int MOD = 1_000_000_007;
        
        // dp[g1][g2] stores the count of pairs with GCD of seq1 = g1 and seq2 = g2
        // A value of 0 means the sequence is currently empty.
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; // Base case: both sequences are empty
        
        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;
                    
                    long currentCount = dp[g1][g2];
                    
                    // Choice 1: Skip 'x' entirely
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + currentCount) % MOD);
                    
                    // Choice 2: Add 'x' to seq1
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    nextDp[ng1][g2] = (int) ((nextDp[ng1][g2] + currentCount) % MOD);
                    
                    // Choice 3: Add 'x' to seq2
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    nextDp[g1][ng2] = (int) ((nextDp[g1][ng2] + currentCount) % MOD);
                }
            }
            dp = nextDp;
        }
        
        // Accumulate all pairs where both subsequences are non-empty (g > 0) and g1 == g2
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }
        
        return (int) totalPairs;
    }
    
    // Quick iterative helper method for GCD calculation
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
