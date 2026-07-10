import java.util.Arrays;

public class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Pair value with original index and sort by value
        Node[] sorted = new Node[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = new Node(nums[i], i);
        }
        Arrays.sort(sorted, (x, y) -> Integer.compare(x.val, y.val));

        // Map original index to sorted position
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sorted[i].id] = i;
        }

        // Step 2: Binary Lifting Setup (LOG = 18 covers 2^17 > 10^5)
        int LOG = 18;
        int[][] st = new int[n][LOG];

        // Two-pointer scan to find the farthest 1-hop right jump
        int r = 0;
        for (int i = 0; i < n; i++) {
            r = Math.max(r, i);
            while (r + 1 < n && sorted[r + 1].val - sorted[i].val <= maxDiff) {
                r++;
            }
            st[i][0] = r;
        }

        // Complete the sparse table for larger powers of 2
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                st[i][j] = st[st[i][j - 1]][j - 1];
            }
        }

        // Step 3: Process queries
        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            int a = pos[u];
            int b = pos[v];

            // Normalize order so we always move right
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (a == b) {
                answer[q] = 0;
                continue;
            }

            int curr = a;
            int steps = 0;

            // Greedily advance using binary lifting staying strictly left of b
            for (int j = LOG - 1; j >= 0; j--) {
                if (st[curr][j] < b) {
                    curr = st[curr][j];
                    steps += (1 << j);
                }
            }

            // Check if one final step bridges the remaining gap
            if (st[curr][0] >= b) {
                answer[q] = steps + 1;
            } else {
                answer[q] = -1;
            }
        }

        return answer;
    }

    private static class Node {
        int val;
        int id;

        Node(int val, int id) {
            this.val = val;
            this.id = id;
        }
    }
}
