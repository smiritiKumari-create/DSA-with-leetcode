import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        for (int shift = 0; shift < k; shift++) {
         
            int[][] nextGrid = new int[m][n];
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (j < n - 1) {
                        
                        nextGrid[i][j + 1] = grid[i][j];
                    } else if (i < m - 1) {
                        
                        nextGrid[i + 1][0] = grid[i][j];
                    } else {
                        
                        nextGrid[0][0] = grid[i][j];
                    }
                }
            }
            grid = nextGrid;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : grid) {
            List<Integer> listRow = new ArrayList<>();
            for (int val : row) {
                listRow.add(val);
            }
            result.add(listRow);
        }
        
        return result;
    }
}
