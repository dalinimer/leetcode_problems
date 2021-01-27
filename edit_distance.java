/*Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character*/
class Solution {
    
    
    public int minDistance(String word1, String word2) {
        int[][] memo=new int[word1.length()][word2.length()];
        for(int i=0;i<memo.length;i++){
            for(int j=0;j<memo[0].length;j++){
                memo[i][j]=-1;
            }
        }
        int n=word1.length();
        int m=word2.length();
        if(n==0 || m==0){
            return Math.max(n,m);
        }
        return memo(n-1,m-1,memo,word1,word2);
        
    }
    public int memo(int i,int j,int[][] memo,String s1,String s2){
            if(memo[i][j]==-1){
                if(i==0 && j==0){
                    if(s1.charAt(i)==s2.charAt(j)){
                        memo[i][j]=0;
                    }
                    else{
                        memo[i][j]=1;
                    }
                }
                else if(i==0){
                    
                    if(s1.charAt(i)==s2.charAt(j)){
                        memo[i][j]=j;
                    }  
                    else{
                       memo[i][j]=memo(i,j-1,memo,s1,s2)+1; 
                    }
                }
                else if(j==0){
                    if(s1.charAt(i)==s2.charAt(j)){
                        memo[i][j]=i;
                    }
                    else{
                        memo[i][j]=memo(i-1,j,memo,s1,s2)+1;
                    }
                  
                }
                else{
                     if(s1.charAt(i)==s2.charAt(j)){
                         memo[i][j]=memo(i-1,j-1,memo,s1,s2);
                     }
                    else{
                        memo[i][j]=memo(i-1,j,memo,s1,s2);
                        memo[i][j]=Math.min(memo[i][j],memo(i,j-1,memo,s1,s2));
                        memo[i][j]=Math.min(memo[i][j],memo(i-1,j-1,memo,s1,s2));
                        memo[i][j]++;
                    }
                    
                }
            }
        return memo[i][j];
    }
   
}