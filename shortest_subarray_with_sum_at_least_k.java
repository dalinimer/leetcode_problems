/*Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.
*/
class Solution {
   
    public int shortestSubarray(int[] A, int K){
        int length=Integer.MAX_VALUE;
        Deque<Integer> queue= new LinkedList<Integer>();
        int[] S=new int[A.length];
        S[0]=A[0];
        if(A[0]>=K){
            return 1;
        }
        for(int i=1;i<A.length;i++){
            S[i]=S[i-1]+A[i];
            if(S[i]>=K){
                length=Math.min(length,i+1);
            }
        }
        queue.addLast(0);
        for(int i=1;i<A.length;i++){
            while(!queue.isEmpty() && S[queue.getFirst()]+K<=S[i]){
                length=Math.min(i-queue.removeFirst(),length);
            }
            while(!queue.isEmpty() && S[queue.getLast()]>S[i]){
                queue.removeLast();
            }
            queue.add(i);
        }
        if(length==Integer.MAX_VALUE){
            return -1;
        }
        else{
            return length;
        }
    }
}