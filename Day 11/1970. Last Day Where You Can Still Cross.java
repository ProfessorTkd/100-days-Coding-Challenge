class Solution {
    public boolean checkPath(byte[][] cell, int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        for(int i = 0; i < row; i++ ){
            if(cell[i][0] == 1){
                queue.add(new int[]{i,0});
                visited[i][0] = true;
            }
        }
        int changeI[] = {1,-1,-1,0,1,-1,0,1};
        int changeJ[] = {0,0,1,1,1,-1,-1,-1};
        while(!queue.isEmpty()){
            int[] q = queue.poll();
            if(q[1] == col - 1){return true;}
            for(int i = 0; i  < 8 ; i++){
                int newI = q[0] + changeI[i];
                int newJ = q[1] + changeJ[i]; 
                if(newI >=0 && newJ >=0 && newI < row && newJ < col && cell[newI][newJ]== 1 && !visited[newI][newJ] ){
                    visited[newI][newJ] = true;
                    queue.add(new int[]{newI, newJ});
                }
            }
            
        }
        return false;


    }
    public int latestDayToCross(int row, int col, int[][] cells) {
        int left = 0;
        int right = cells.length - 1;
        int prev = 0;
        byte[][] cell = new byte[row][col];

        while(left <= right){
            int mid = (left + right )/2;
            
            if(mid < prev){
                for(int i = mid + 1; i < prev ; i++){
                    cell[cells[i][0]-1][cells[i][1]-1] = 0;
                }
            }
            else{
                for(int i = prev; i <= mid; i++){
                    cell[cells[i][0]-1][cells[i][1]-1] = 1;
                }
            }
            prev = mid + 1;
            // cell = new byte[row][col];
            // for(int i = 0; i <= mid; i++){
            //     cell[cells[i][0]-1][cells[i][1]-1] = 1;
            // }
            if(!checkPath(cell, row, col)){
                left = mid + 1;
            }
            else{
                right = mid - 1 ;
            }
        }
        return right + 1;
        
    }
}
