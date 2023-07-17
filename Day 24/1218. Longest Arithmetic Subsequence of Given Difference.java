class Solution {
    public int longestSubsequence(int[] arr, int diff) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            int val = map.getOrDefault(arr[i]-diff, 0) + 1;
            max = Math.max(max, val);
            map.put(arr[i], val);
        }
        return max;
    }
}
