class Solution {
    List<int[]>[] g;
    int[] topo;
    int n;
    boolean[] online;
    long k;

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.n = online.length;
        this.online = online;
        this.k = k;

        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        int[] indeg = new int[n];
        int maxCost = 0;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int c = e[2];

            g[u].add(new int[]{v, c});
            indeg[v]++;
            maxCost = Math.max(maxCost, c);
        }

        // Topological sort
        topo = new int[n];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) q.offer(i);
        }

        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (int[] e : g[u]) {
                int v = e[0];
                if (--indeg[v] == 0) {
                    q.offer(v);
                }
            }
        }

        int lo = 0, hi = maxCost;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (can(mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int x) {
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == INF) continue;

            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (int[] e : g[u]) {
                int v = e[0];
                int c = e[1];

                if (c < x) continue;
                if (v != n - 1 && !online[v]) continue;

                long nd = dist[u] + c;
                if (nd < dist[v]) {
                    dist[v] = nd;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}