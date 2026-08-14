class Solution {
    public int maximumLengthSubstring(String s) {
        int[] counts = new int[26];
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            int rightIdx = s.charAt(right) - 'a';
            counts[rightIdx]++;
            
            
            while (counts[rightIdx] > 2) {
                int leftIdx = s.charAt(left) - 'a';
                counts[leftIdx]--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
