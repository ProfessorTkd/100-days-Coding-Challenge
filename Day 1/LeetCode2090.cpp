class Solution {
public:
    vector<int> getAverages(vector<int>& nums, int k) {
    int n = nums.size();
    std::vector<int> ans(n, -1);
    
    long long sum = 0;
    int length = 2 * k + 1;
    if (length > n)
        return ans;
    
    for (int i = 0; i < length; i++) {
        sum += nums[i];
    }
    ans[k] = static_cast<int>(sum / length);
    
    int start = 0;
    for (int last = length; last < n; last++) {
        sum = sum - nums[start] + nums[last];
        start++;
        ans[last - k] = static_cast<int>(sum / length);
    }
    
    return ans;
    }
};
