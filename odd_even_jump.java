/*You are given an integer array A. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.

You may jump forward from index i to index j (with i < j) in the following way:

During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
It may be the case that for some index i, there are no legal jumps.
A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once).*/

class Solution {
    public class NodeOdd implements Comparable<NodeOdd>{
        public int val;
        public int index;
        
        public NodeOdd(int v, int i){
            val=v;
            index=i;
        }
        
        public int compareTo(NodeOdd other){
            if(other.val<val){
                return -1;
            }
            else if(other.val>val){
                return 1;
            }
            else{
                if(other.index<index){
                    return  1;
                }
                else if(other.index>index){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        }
    } 
    public class NodeEven implements Comparable<NodeEven>{
        public int val;
        public int index;
        
        public NodeEven(int v, int i){
            val=v;
            index=i;
        }
        
        public int compareTo(NodeEven other){
            if(other.val<val){
                return -1;
            }
            else if(other.val>val){
                return 1;
            }
            else{
                if(other.index<index){
                    return  -1;
                }
                else if(other.index>index){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
    }
    public int oddEvenJumps(int[] A) {
        int[] nextOdd=new int[A.length];
        int[] nextEven=new int[A.length];
        TreeSet<NodeOdd> oddN=new TreeSet<NodeOdd>();
        TreeSet<NodeEven> evenN=new TreeSet<NodeEven>();
        nextOdd[A.length-1]=-1;
        nextEven[A.length-1]=-1;
        oddN.add(new NodeOdd(A[A.length-1],A.length-1));
        evenN.add(new NodeEven(A[A.length-1],A.length-1));
        for(int i=A.length-2;i>=0;i--){
            NodeOdd newOdd=new NodeOdd(A[i],i);
            NodeEven newEven=new NodeEven(A[i],i);
            NodeOdd no=oddN.higher(newOdd);
            NodeEven ne=evenN.lower(newEven);
            if(ne==null){
                nextOdd[i]=-1;
            }
            else{
                nextOdd[i]=ne.index;
            }
            if(no==null){
                nextEven[i]=-1;
            }
            else{
                nextEven[i]=no.index;
            }
            oddN.add(newOdd);
            evenN.add(newEven);
        }
        System.out.println(Arrays.toString(nextEven));
        System.out.println(Arrays.toString(nextOdd));
        
        int[] fEven=new int[A.length];
        int[] fOdd=new int[A.length];
        fEven[A.length-1]=1;
        fOdd[A.length-1]=1;
        int count=0;
        for(int i=0;i<A.length;i++){
            if(f(i,fEven,fOdd,nextEven,nextOdd,'o')==1){
                count++;
            }
        }
        System.out.print(Arrays.toString(fOdd));
        return count;
        
    }
    public int f(int index,int[] fEven, int[] fOdd, int[] nextEven, int[] nextOdd, char c){
       if(c=='o'){
            if(fOdd[index]==0){
                int i=nextOdd[index];
                if(i==-1){
                    fOdd[index]=-1;    
                }
                else{
                    fOdd[index]=f(i,fEven,fOdd,nextEven,nextOdd,'e'); 
                }
            }   
            return fOdd[index];
        }
        else{
            if(fEven[index]==0){
                int i=nextEven[index];
                if(i==-1){
                    fEven[index]=-1; 
                }
                else{
                    fEven[index]=f(i,fEven,fOdd,nextEven,nextOdd,'o');
                }
            }
            return fEven[index]; 
        }
        
    }
}