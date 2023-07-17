class Solution {
    public int maxConsecutiveAnswers(String ansK, int k) {
        int start = 0;
        int tsum = 0;
        int fsum = 0;
        int max = 0;
        for(int i = 0; i < ansK.length(); i++){
            char c= ansK.charAt(i);
            if(c == 'T'){
                tsum += 1;  
            } else{
                fsum += 1;
            }

            while(start < ansK.length() && Math.min(tsum, fsum) > k){
                char cs = ansK.charAt(start);
                start++;
                if(cs == 'T'){
                    tsum -= 1;  
                } else{
                    fsum -= 1;
                }
            }
            
            if(Math.min(tsum, fsum) <= k)
                max = Math.max(max, i - start + 1);
        }

        return max;
    }
}
