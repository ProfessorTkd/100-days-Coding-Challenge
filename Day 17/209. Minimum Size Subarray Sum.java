class Solution {
    public int minSubArrayLen(int target, int[] nums) {
       int sum = 0, from = 0, ans = Integer.MAX_VALUE;
       for(int i = 0; i < nums.length; i ++) {
           sum += nums[i];
           while(sum >= target) {
               ans = Math.min(ans, i - from + 1);
               sum -= nums[from ++];
           }
       } 
       return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
