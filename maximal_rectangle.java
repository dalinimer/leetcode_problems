/*Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.*/

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int area=0;
        if(matrix.length==0){
            return 0;
        }
        int[] width=new int[matrix.length];
        for(int j=matrix[0].length-1;j>=0;j--){
            for(int i=matrix.length-1;i>=0;i--){
                if(matrix[i][j]=='1'){
                    width[i]++;
                }
                else{
                    width[i]=0;
                }
                int w=Integer.MAX_VALUE;
                int k=i;
                while(k<matrix.length && width[k]>0){
                    w=Math.min(w,width[k]);
                    area=Math.max(area,w*(k-i+1));
                    k++;
                }
            }
        }
        return area;
    }
    
}