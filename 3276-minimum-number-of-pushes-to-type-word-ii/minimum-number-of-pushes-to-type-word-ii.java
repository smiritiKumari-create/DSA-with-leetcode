import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
   
        int[] freq = new int[26];
        for (int i = 0; i < word.length(); i++) {
            freq[word.charAt(i) - 'a']++;
        }
        
        Arrays.sort(freq);
        
        int totalPushes = 0;
        int lettersMapped = 0;
        
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break; 
            
            int multiplier = (lettersMapped / 8) + 1;
            totalPushes += freq[i] * multiplier;
            
            lettersMapped++;
        }
        
        return totalPushes;
    }
}
