class Solution {
    static final int STATE_UNDEFINED  =  0;
    static final int STATE_BAD        = -1;
    static final int STATE_SAFE       =  1;
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] states = new int[n];
        for (int i = n - 1; i >= 0; i--) 
            if (states[i] == STATE_UNDEFINED) 
                dfs(states, graph, i);
        
        List<Integer> result = new ArrayList();
        for (int i = 0; i < n; i++) 
            if (states[i] > 0)
                result.add(i);
        return result;
    }
    
    private int dfs(int[] states, int[][] graph, int node) {
        states[node] = STATE_BAD;
        int state = STATE_SAFE;
        for (int child : graph[node]) 
            if (states[child] <= 0 && ((state |= states[child]) < 0 || 
                        (state |= dfs(states, graph, child)) < 0))
                break;
        states[node] = state;
        return state;
    }
}
