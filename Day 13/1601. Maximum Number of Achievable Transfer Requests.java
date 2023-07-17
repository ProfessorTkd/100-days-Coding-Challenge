class Solution {

    int maxCount = 0;
    public int maximumRequests(int n, int[][] requests) {

        int[] freq = new int[n];
        backtrack(requests, freq, 0, 0);
        return maxCount;

    }
    public void backtrack(int[][] reqs, int[] freq, int index, int count){

        if(index == reqs.length){
            for(int f: freq) if(f != 0) return;
            maxCount = Math.max(maxCount, count);
            return;
        }

        int[] currReq = reqs[index];
        --freq[currReq[0]]; ++freq[currReq[1]];
        backtrack(reqs, freq, index + 1, count + 1);
        ++freq[currReq[0]]; --freq[currReq[1]];
        backtrack(reqs, freq, index + 1, count);

    }
}
