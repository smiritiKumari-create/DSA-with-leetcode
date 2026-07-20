// class Solution {
//     public int uniquePaths(int m, int n) {
//         int[] dp = new int[n];
//         for (int j = 0; j < n; j++) {
//             dp[j] = 1;
//         }
        
//         for (int i = 1; i < m; i++) {
//             for (int j = 1; j < n; j++) {
//                 dp[j] = dp[j] + dp[j - 1];
//             }
//         }
//         return dp[n - 1];
//     }
// }
class Solution{
    int[][] dp;
    public int uniquePaths(int m,int n){
        dp=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dp[i][j]=-1;
            }
        }
        return solve(0,0,m,n);
    }
    public int solve(int row,int col,int m,int n){
        if(row==m-1 && col==n-1)
        return 1;
        if(row>=m || col>=n)
        return 0;
        if(dp[row][col]!=-1)
        return dp[row][col];
        int right=solve(row,col+1,m,n);
        int down=solve(row+1,col,m,n);
        dp[row][col]=right+down;
        return dp[row][col];

    }
}
