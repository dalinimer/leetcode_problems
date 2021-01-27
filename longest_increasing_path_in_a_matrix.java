/*Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

*/

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0){
            return 0;
        }
        int[][] memo=new int[matrix.length][matrix[0].length];
        int out=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                out=Math.max(out,dp(matrix,memo,i,j));
            }
        }
        return out;
    }
    
    public int dp(int[][] matrix, int[][] memo, int i, int j){
       if(memo[i][j]==0){
           int max=0;
           if(i+1<matrix.length && matrix[i+1][j]>matrix[i][j]){
               max=Math.max(max,dp(matrix,memo,i+1,j));
           }
           if(i>0 && matrix[i-1][j]>matrix[i][j]){
               max=Math.max(max,dp(matrix,memo,i-1,j));
           }
           if(j+1<matrix[0].length && matrix[i][j+1]>matrix[i][j]){
               max=Math.max(max,dp(matrix,memo,i,j+1));
           }
           if(j-1>=0 && matrix[i][j-1]>matrix[i][j]){
               max=Math.max(max,dp(matrix,memo,i,j-1));
           }
           max++;
           memo[i][j]=max;
       }
        return memo[i][j];
        
    }
}