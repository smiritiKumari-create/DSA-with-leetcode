class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int maxEl = 0;
        for (int num : nums) {
            maxEl = Math.max(maxEl, num);
        }
        int maxPossibleXor = 1;
        while (maxPossibleXor <= maxEl) {
            maxPossibleXor <<= 1;
        }
        boolean[] twoXors = new boolean[maxPossibleXor];
        boolean[] triplets = new boolean[maxPossibleXor];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                twoXors[nums[i] ^ nums[j]] = true;
            }
        }
        for (int i = 0; i < maxPossibleXor; i++) {
            if (twoXors[i]) {
                for (int num : nums) {
                    triplets[i ^ num] = true;
                }
            }
        }
        int uniqueCount = 0;
        for (int i = 0; i < maxPossibleXor; i++) {
            if (triplets[i]) {
                uniqueCount++;
            }
        }
        
        return uniqueCount;
    }
}
