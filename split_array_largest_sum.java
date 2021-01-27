/*Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.

 */

class Solution {
    public int splitArray(int[] nums, int m) {
        int[] s=new int[nums.length];
        int n=nums.length;
        s[0]=nums[0];
        for(int i=1;i<s.length;i++){
            s[i]=s[i-1]+nums[i];
        }
        int[] mu=new int[nums.length];
        mu[0]=s[n-1];
        for(int j=1;j<n;j++){
            mu[j]=s[n-1]-s[j-1];
        }
        for(int k=2;k<=m;k++){
            for(int i=m-k;i<=n-k;i++){
                mu[i]=Integer.MAX_VALUE;
                for(int l=i;l<=n-k;l++){
                    int max=mu[l+1];
                    if(i==0){
                        max=Math.max(max,s[l]);
                    }
                    else{
                        max=Math.max(max,s[l]-s[i-1]);
                    }
                    
                    mu[i]=Math.min(mu[i],max);
                }
            }
        }
        return mu[0];
        
    }
   
    
    
}