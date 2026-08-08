import java.util.Arrays;

public class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // last_idx[j] stores the maximum index in word1 from which 
        // the suffix word2[j...m-1] can be formed EXACTLY.
        int[] last_idx = new int[m];
        Arrays.fill(last_idx, -1);
        
        // Populate last_idx from right to left
        int w1Ptr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (w1Ptr >= 0 && word1.charAt(w1Ptr) != word2.charAt(j)) {
                w1Ptr--;
            }
            if (w1Ptr >= 0) {
                last_idx[j] = w1Ptr;
                w1Ptr--; 
            } else {
               
                break;
            }
        }
        
        int[] ans = new int[m];
        int j = 0; 
        boolean changed = false; 
        for (int i = 0; i < n && j < m; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(j);
            
            if (c1 == c2) {
                ans[j] = i;
                j++;
            } else if (!changed) {
               
                if (j == m - 1 || last_idx[j + 1] > i) {
                    ans[j] = i;
                    j++;
                    changed = true; 
                }
            }
        }
        return (j == m) ? ans : new int[0];
    }
}
