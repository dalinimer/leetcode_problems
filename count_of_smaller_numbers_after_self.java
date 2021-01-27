/*You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
*/
class Solution {
    public class Node{
        public int val;
        public int index;
        
        public Node(int v,int i){
            val=v;
            index=i;
        }
        
       
    }
    public List<Integer> countSmaller(int[] nums) {
        int[] count=new int[nums.length];
        Node[] a= new Node[nums.length];
        
        for(int i=0;i<nums.length;i++){
            Node n=new Node(nums[i],i);
            a[i]=n;
        }
        mergeSort(count,a,0,nums.length-1);
        ArrayList<Integer> out=new ArrayList<Integer>();
        for(int i=0;i<a.length;i++){
            out.add(count[i]);   
        }
       
        return out;
    }
    public void mergeSort(int[] c, Node[] nums, int i,int j){
        if(i<j){
            int m=(i+j)/2;
            mergeSort(c,nums,i,m);
            mergeSort(c,nums,m+1,j);
            merge(c,nums,i,m,j);
        }
    }
    public void merge(int[] c,Node[] nums,int i,int m,int j){
        int index=i;
        Node[] left=new Node[m-i+1];
        Node[] right=new Node[j-m];
        for(int k=i;k<j+1;k++){
            if(k<=m){
                left[k-i]=nums[k];
            }
            if(k>m){
                right[k-m-1]=nums[k];
            }
        }
        int i1=i;
        int j1=m+1;
        int count=0;
        while(index<j+1){
            while(i1<m+1 && (j1>j||left[i1-i].val<=right[j1-m-1].val)){
                nums[index]=left[i1-i];
                if(count>0){
                    c[nums[index].index]+=count;
                }
                index++;
                i1++;
            }
            while(j1<j+1 && (i1>m || left[i1-i].val>right[j1-m-1].val)){
                count++;
                nums[index]=right[j1-m-1];
                index++;
                j1++;
            }
        }
    }
}
 