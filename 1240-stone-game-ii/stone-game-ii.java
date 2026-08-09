import java.util.HashMap;
import java.util.Map;

public class Solution {
    private int[] suffixSums;
    private int[][] memo;
    private int n;

    public int stoneGameII(int[] piles) {
        this.n = piles.length;
        this.suffixSums = new int[n + 1];
        
        // Calculate suffix sums for quick remaining stone retrieval
        for (int i = n - 1; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + piles[i];
        }
        
        // M can at most reach n, so memo table size is n x (n + 1)
        this.memo = new int[n][n + 1];
        
        return dp(0, 1);
    }

    private int dp(int i, int m) {
        // Base case: if we can take all remaining piles, take them all
        if (i + 2 * m >= n) {
            return suffixSums[i];
        }
        
        // Return cached result if already calculated
        if (memo[i][m] > 0) {
            return memo[i][m];
        }
        
        int maxStones = 0;
        
        // Try taking X piles where 1 <= X <= 2M
        for (int x = 1; x <= 2 * m; x++) {
            // Opponent's optimal score from the remaining piles
            int opponentScore = dp(i + x, Math.max(m, x));
          
            int currentScore = suffixSums[i] - opponentScore;
            
            maxStones = Math.max(maxStones, currentScore);
        }
        
        memo[i][m] = maxStones;
        return maxStones;
    }
}
