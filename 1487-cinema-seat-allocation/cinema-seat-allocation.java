import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row ID to its seat reservation bitmask
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }
        
        // Assume every row can hold 2 families initially
        // Use long for calculation to prevent potential overflows, though max result fits in int
        long totalGroups = 2L * n;
        
        int leftMask = 60;    // Seats 2, 3, 4, 5
        int rightMask = 960;  // Seats 6, 7, 8, 9
        int middleMask = 240; // Seats 4, 5, 6, 7
        
        for (int mask : rowMasks.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;
            
            int actualGroups = 0;
            if (leftFree && rightFree) {
                actualGroups = 2;
            } else if (leftFree || rightFree || middleFree) {
                actualGroups = 1;
            }
            
            // Deduct the 2 default spots and add the valid ones
            totalGroups += (actualGroups - 2);
        }
        
        return (int) totalGroups;
    }
}
