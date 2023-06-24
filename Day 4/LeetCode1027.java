class Solution {
    public int longestArithSeqLength(int[] nums) {
        int ans = 2;
        int n = nums.length;
        Map<Integer, Integer>[] dp = new HashMap[n];
        for(int i = 0 ; i < n ; i++){
            dp[i] = new HashMap<>();
            for(int j = 0 ; j < i ; j++){
                int diffrence = nums[i] - nums[j];
                dp[i].put(diffrence, dp[j].getOrDefault(diffrence, 1) + 1);
                ans = Math.max(ans, dp[i].get(diffrence));
            }
        }
        return ans;
    }
}
