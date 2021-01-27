/* On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1. */

class Solution {
    public int slidingPuzzle(int[][] board) {
        State initialS=new State(board);
        initialS.dist=0;
       
        Queue<State> toVisit=new LinkedList<State>();
         toVisit.add(initialS);
        HashSet<Integer> visited=new HashSet<Integer>();
        while(!toVisit.isEmpty() && !toVisit.peek().isFinal()){
            State current=toVisit.remove();
            if(!visited.contains(current.auxHash())){
                visited.add(current.auxHash());
                current.getNeighboringStates();
                for(State neighbor:current.neighStates){
                    if(!visited.contains(neighbor.auxHash())){
                        neighbor.dist=current.dist+1;
                        toVisit.add(neighbor);
                    }
                }
            }
        }
        if(toVisit.isEmpty()){
            return -1;
        }
        else{
            return toVisit.remove().dist;
        }
    
    }
    
    public class State{
        public int[][] board;
        public int[] zeroPosition;
        public ArrayList<State> neighStates;
        public int dist;
        
        public State(int[][] state){
            board=state;
            dist=-1;
            zeroPosition=new int[2];
            for(int i=0;i<state.length;i++){
                for(int j=0;j<state[0].length;j++){
                    if(state[i][j]==0){
                        zeroPosition[0]=i;
                        zeroPosition[1]=j;
                    }
                }
            }
            neighStates=new ArrayList<State>();
        } 
        
        public void getNeighboringStates(){
            int i=zeroPosition[0];
            int j=zeroPosition[1];
            if(i==0){
                State neighbor=new State(copyBoard());
                neighbor.board[i][j]=neighbor.board[i+1][j];
                neighbor.board[i+1][j]=0;
                neighbor.zeroPosition[0]=i+1;
                neighbor.zeroPosition[1]=j;
                neighStates.add(neighbor);
            }
            if(j+1<3){
                State neighbor2=new State(copyBoard());
                neighbor2.board[i][j]=neighbor2.board[i][j+1];
                neighbor2.board[i][j+1]=0;
                neighbor2.zeroPosition[0]=i;
                neighbor2.zeroPosition[1]=j+1;
                neighStates.add(neighbor2);
            }
            if(i==1){
                State neighbor3=new State(copyBoard());
                neighbor3.board[i][j]=neighbor3.board[i-1][j];
                neighbor3.board[i-1][j]=0;
                neighbor3.zeroPosition[0]=i-1;
                neighbor3.zeroPosition[1]=j;
                neighStates.add(neighbor3);
            }
            if(j-1>=0){
                State neighbor4=new State(copyBoard());
                neighbor4.board[i][j]=neighbor4.board[i][j-1];
                neighbor4.board[i][j-1]=0;
                neighbor4.zeroPosition[0]=i;
                neighbor4.zeroPosition[1]=j-1;
                neighStates.add(neighbor4);
            }
            
        }
        
        public int[][] copyBoard(){
            int[][] newCopy=new int[2][3];
            for(int i=0;i<2;i++){
                for(int j=0;j<3;j++){
                    newCopy[i][j]=board[i][j];
                }
            }
            return newCopy;
        }
        
       
        
        public int auxHash(){
            int output=0;
            for(int j=0;j<3;j++){
                output+=board[0][j]*Math.pow(10,5-j);
            }
            for(int j=0;j<3;j++){
                output+=board[1][j]*Math.pow(10,2-j);
            }
            return output;
        }
        
        public boolean isFinal(){
            for(int j=0;j<3;j++){
                if(board[0][j]!=j+1){
                    return false;
                }
            }
            for(int j=0;j<2;j++){
                if(board[1][j]!=4+j){
                    return false;
                }
            }
            return (board[1][2]==0);
        }
    }
    
}