class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones_total = 0;
        for (char c : s.toCharArray()) if (c == '1') ones_total++;
        
        List<int[]> zeroRuns = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') i++;
                zeroRuns.add(new int[]{start, i - 1});
            } else {
                i++;
            }
        }
        
        List<int[]> pairs = new ArrayList<>();
        for (int k = 0; k < zeroRuns.size() - 1; k++) {
            int[] z1 = zeroRuns.get(k);
            int[] z2 = zeroRuns.get(k + 1);
            int sumLen = (z1[1] - z1[0] + 1) + (z2[1] - z2[0] + 1);
            pairs.add(new int[]{z1[0], z1[1], z2[0], z2[1], sumLen});
        }
        
        int numPairs = pairs.size();
        int qLen = queries.length;
        List<Integer> ans = new ArrayList<>();
        
        if (numPairs == 0) {
            for (int q = 0; q < qLen; q++) {
                ans.add(ones_total);
            }
            return ans;
        }
        
        int K = 32 - Integer.numberOfLeadingZeros(numPairs);
        int[][] st = new int[numPairs][K];
        for (int j = 0; j < numPairs; j++) {
            st[j][0] = pairs.get(j)[4];
        }
        
        for (int j = 1; j < K; j++) {
            for (int idx = 0; idx <= numPairs - (1 << j); idx++) {
                st[idx][j] = Math.max(st[idx][j - 1], st[idx + (1 << (j - 1))][j - 1]);
            }
        }
        
        int[] r1List = new int[numPairs];
        int[] l2List = new int[numPairs];
        for (int j = 0; j < numPairs; j++) {
            r1List[j] = pairs.get(j)[1];
            l2List[j] = pairs.get(j)[2];
        }
        
        for (int q = 0; q < qLen; q++) {
            int l = queries[q][0], r = queries[q][1];
            int firstK = lowerBound(r1List, l);
            int lastK = upperBound(l2List, r) - 1;
            
            if (firstK > lastK) {
                ans.add(ones_total);
                continue;
            }
            
            int bestGain = 0;
            if (firstK == lastK) {
                int[] p = pairs.get(firstK);
                int gain = (p[1] - Math.max(p[0], l) + 1) + (Math.min(p[3], r) - p[2] + 1);
                bestGain = Math.max(bestGain, gain);
            } else {
                int[] p1 = pairs.get(firstK);
                int gain1 = (p1[1] - Math.max(p1[0], l) + 1) + (Math.min(p1[3], r) - p1[2] + 1);
                bestGain = Math.max(bestGain, gain1);
                
                int[] p2 = pairs.get(lastK);
                int gain2 = (p2[1] - Math.max(p2[0], l) + 1) + (Math.min(p2[3], r) - p2[2] + 1);
                bestGain = Math.max(bestGain, gain2);
                
                if (firstK + 1 <= lastK - 1) {
                    bestGain = Math.max(bestGain, querySt(st, firstK + 1, lastK - 1));
                }
            }
            ans.add(ones_total + bestGain);
        }
        return ans;
    }
    
    private int querySt(int[][] st, int L, int R) {
        if (L > R) return 0;
        int len = R - L + 1;
        int j = 31 - Integer.numberOfLeadingZeros(len);
        return Math.max(st[L][j], st[R - (1 << j) + 1][j]);
    }
    
    private int lowerBound(int[] arr, int val) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= val) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    
    private int upperBound(int[] arr, int val) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > val) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}