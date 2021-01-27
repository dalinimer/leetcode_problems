/*Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

Follow up:

Could you solve the problem in O(1) extra memory space?
You may not alter the values in the list's nodes, only nodes itself may be changed.*/

class Solution {
    public int trap(int[] height) {
        if(height.length==0){
            return 0;
        }
        int[] max=new int[height.length];
        max[height.length-1]=-1;
        for(int i=height.length-2;i>=0;i--){
            max[i]=Math.max(height[i+1],max[i+1]);
        }
        int water=0;
        int i=0;
        while(i<height.length && height[i]==0){
            i++;
        }

        while(i<height.length){
            if(max[i]>=height[i]){
                int j=i+1;
                while(j<height.length && height[j]<height[i]){
                    water+=(height[i]-height[j]);
                    j++;
                }
                i=j;
            }
            else{
                int k=i+1;
                while(k<height.length && max[k]==max[i]){
                    water+=(max[i]-height[k]);
                    k++;
                }
                i=k;
            }
        }
        return water;
    }
}