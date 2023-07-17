class Solution {
    int res = Integer.MAX_VALUE;
    
    public int distributeCookies(int[] cookies, int k) {
        dfs(cookies, new int [k], 0, 0);
        return res;
    }
    
    public void dfs(int [] cookies, int [] children, int idx, int max) {
        if (idx >= cookies.length) {
            res = Math.min(res, max);
            return;
        }
        
        int c = cookies[idx];
        for (int i = 0; i < children.length; i++) {
            children[i] += c;
            dfs(cookies, children, idx + 1, Math.max(max, children[i]));
            children[i] -= c;
            if(children[i]==0) return; // each child must get atleast one cookie
        }
    }
}
