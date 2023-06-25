class Solution {
    private static final int MOD = 1000000007;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        int[][] dp = new int[n][fuel + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return dfs(start, finish, fuel, locations, dp);
    }

    private int dfs(int i, int finish, int fuel, int[] locations, int[][] dp) {
        if (fuel < 0)
            return 0;
        if (dp[i][fuel] != -1)
            return dp[i][fuel];
        int ans = 0;
        if (i == finish)
            ans++;
        for (int j = 0; j < locations.length; j++) {
            if (j == i)
                continue;
            ans = (ans + dfs(j, finish, fuel - Math.abs(locations[i] - locations[j]), locations, dp)) % MOD;
        }
        dp[i][fuel] = ans;
        return ans;     
    }
}
