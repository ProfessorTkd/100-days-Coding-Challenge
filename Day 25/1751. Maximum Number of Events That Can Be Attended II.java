class Solution {

    public int maxValue(int[][] events, int k) {

        Arrays.sort(events, (a, b) -> a[0] - b[0]);   
        return dfs(0, 0, k, events, new HashMap<String, Integer>());
    }
  
    public int dfs(int currIdx, int prevEndTime, int count, int[][] events, HashMap<String, Integer> map){

        if(count==0 || currIdx==events.length)
        return 0;

        if(prevEndTime >= events[currIdx][0])
        return dfs(currIdx+1, prevEndTime, count, events, map);

        String key = Integer.toString(currIdx) + "_" + Integer.toString(count);

        if(map.containsKey(key))
        return map.get(key);

        int consider = events[currIdx][2] + dfs(currIdx+1, events[currIdx][1], count-1, events, map);

        int skip = dfs(currIdx+1, prevEndTime, count, events, map);

        map.put(key, Math.max(consider, skip));
        return map.get(key);
    }

}
