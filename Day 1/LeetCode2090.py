class Solution(object):
    def getAverages(self, nums, k):
            n = len(nums)
            ans = [-1] * n
            length = 2 * k + 1
            if length > n:
                return ans

            total_sum = sum(nums[:length])
            ans[k] = total_sum // length
            start = 0
            for last in range(length, n):
                total_sum = total_sum - nums[start] + nums[last]
                start += 1
                ans[last - k] = total_sum // length

            return ans
        
