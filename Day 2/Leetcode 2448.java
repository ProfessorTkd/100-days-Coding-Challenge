class Solution {
    public long minCost(int[] nums, int[] cost) {
        int left=Integer.MAX_VALUE,right=Integer.MIN_VALUE;
        for(int i:nums){
            left=Math.min(left,i);
            right=Math.max(right,i);
        }
        long ans=0;
        while(left<right){
            int mid=(right+left)/2;
            long cost1=binarySearch(nums,cost,mid);
            long cost2=binarySearch(nums,cost,mid+1);
            if(cost1<=cost2){
                ans=cost1;
                right=mid;
            }
            else{
                left=mid+1;
                ans=cost2;
            }
        }
        return ans;
    }
    public long binarySearch(int [] nums,int [] cost,int ele){
        long total=0L;
        for(int i=0;i<nums.length;i++) 
            total+=(1L*Math.abs(nums[i]-ele)*cost[i]);
        return total;
    }
}
