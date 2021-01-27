/* Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

Binary matrix is a matrix with all cells equal to 0 or 1 only.

Zero matrix is a matrix with all cells equal to 0.*/
class Solution {
    public int minFlips(int[][] mat) {
         if(isFlipped(mat)){
             return 0;
         }
        ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
              
                    ArrayList<Integer> position=new ArrayList<Integer>();
                    position.add(i);
                    position.add(j);
                    list.add(position);
                
            }
        }
        ArrayList<ArrayList<Integer>> indices=new ArrayList<ArrayList<Integer>>();
        for(int i=1;i<list.size()+1;i++){
            permutations(indices,new ArrayList<Integer>(),0,i,list.size());
        }
       
        int[][] testMatrix=new int[mat.length][mat[0].length];
        for(int i=0;i<indices.size();i++){
            copyMatrix(mat,testMatrix);
            ArrayList<Integer> permList=indices.get(i);
            for(int j=0;j<permList.size();j++){
                ArrayList<Integer> currentPos=list.get(permList.get(j));
                
                flip(testMatrix,currentPos.get(0),currentPos.get(1));
            }
            
            
            if(isFlipped(testMatrix)){
                    return  permList.size();
            }
            
        }
        return -1;
        
        
    }
    
    public void copyMatrix(int[][] m, int[][] a){
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[i].length;j++){
                a[i][j]=m[i][j];
            }
        }
    }
    public void permutations(ArrayList<ArrayList<Integer>> perms, ArrayList<Integer> currentList, int index, int size, int totalSize){
       
        if(currentList.size()<size){
            for(int j=index;j<totalSize;j++){
                ArrayList<Integer> newList=new ArrayList<Integer>();
                currentList.add(j);
                newList.addAll(currentList);
                permutations(perms,newList,j+1,size,totalSize);
                currentList.remove(currentList.size()-1);
            }
        }
        else{
            perms.add(currentList);
        }
    }
   
    public boolean isFlipped(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(a[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void flip(int[][] a, int i, int j){
        a[i][j]=(a[i][j]+1)%2;
        if(i+1<a.length){
            a[i+1][j]=(a[i+1][j]+1)%2;
        }
        if(i-1>=0){
            a[i-1][j]=(a[i-1][j]+1)%2;
        }
        if(j+1<a[0].length){
            a[i][j+1]=(a[i][j+1]+1)%2;
        }
        if(j-1>=0){
            a[i][j-1]=(a[i][j-1]+1)%2;
        }
    }
}