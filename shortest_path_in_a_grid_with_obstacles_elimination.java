/*Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.*/

import java.awt.*;
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m=grid.length;
        int n=grid[0].length;
        int[][][] memoForw=new int[m][n][k+1];    
        int[][][] memoBack=new int[m][n][k+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                for(int l=0;l<k+1;l++){
                    if(i==0 && j==0){
                        memoForw[i][j][l]=0;
                    }
                    else{
                        memoForw[i][j][l]=Integer.MAX_VALUE;
                    }
                     if(i==m-1 && j==n-1){
                        memoBack[i][j][l]=0;
                    }
                    else{
                        memoBack[i][j][l]=Integer.MAX_VALUE;;
                    }
                   
                }
            }
        }
        HashSet<Point> visitedForw=new HashSet<Point>();
        HashSet<Point> visitedBack=new HashSet<Point>();
        Queue<Point> toVisitForw=new LinkedList<Point>();
        Queue<Point> toVisitBack=new LinkedList<Point>();
        Queue<Integer> obstaclesForw=new LinkedList<Integer>();
        Queue<Integer> obstaclesBack=new LinkedList<Integer>();
        
        toVisitForw.add(new Point(0,0));
        obstaclesForw.add(0);
        while(!toVisitForw.isEmpty()){
            Point current=toVisitForw.remove();
            int obst=obstaclesForw.remove();
            if(!visitedForw.contains(current)){
                visitedForw.add(current);
                int i=current.x;
                int j=current.y;
                if(i+1<m){
                    if(grid[i+1][j]+obst<=k){
                        memoForw[i+1][j][obst+grid[i+1][j]]=Math.min(memoForw[i+1][j][obst+grid[i+1][j]],plus(memoForw[i][j][obst]));
                        toVisitForw.add(new Point(i+1,j));
                        obstaclesForw.add(obst+grid[i+1][j]);
                    }
                }
                if(i-1>=0){
                    if(grid[i-1][j]+obst<=k){
                        memoForw[i-1][j][obst+grid[i-1][j]]=Math.min(memoForw[i-1][j][obst+grid[i-1][j]],plus(memoForw[i][j][obst]));
                        toVisitForw.add(new Point(i-1,j));
                        obstaclesForw.add(obst+grid[i-1][j]);
                    }
                }
                if(j+1<n){
                    if(grid[i][j+1]+obst<=k){
                        memoForw[i][j+1][obst+grid[i][j+1]]=Math.min(memoForw[i][j+1][obst+grid[i][j+1]],plus(memoForw[i][j][obst]));
                        toVisitForw.add(new Point(i,j+1));
                        obstaclesForw.add(obst+grid[i][j+1]);
                    }
                }
                if(j-1>=0){
                    if(grid[i][j-1]+obst<=k){
                        memoForw[i][j-1][obst+grid[i][j-1]]=Math.min(memoForw[i][j-1][obst+grid[i][j-1]],plus(memoForw[i][j][obst]));
                        toVisitForw.add(new Point(i,j-1));
                        obstaclesForw.add(obst+grid[i][j-1]);
                    }
                }
            }
        }
        
        toVisitBack.add(new Point(m-1,n-1));
        obstaclesBack.add(0);
        while(!toVisitBack.isEmpty() ){
            Point current=toVisitBack.remove();
            int obst=obstaclesBack.remove();
            if(!visitedBack.contains(current)){
                visitedBack.add(current);
                int i=current.x;
                int j=current.y;
                if(i+1<m){
                    if(grid[i+1][j]+obst<=k){
                        memoBack[i+1][j][obst+grid[i+1][j]]=Math.min(memoBack[i+1][j][obst+grid[i+1][j]],plus(memoBack[i][j][obst]));
                        toVisitBack.add(new Point(i+1,j));
                        obstaclesBack.add(obst+grid[i+1][j]);
                    }
                }
                if(i-1>=0){
                    if(grid[i-1][j]+obst<=k){
                        memoBack[i-1][j][obst+grid[i-1][j]]=Math.min(memoBack[i-1][j][obst+grid[i-1][j]],plus(memoBack[i][j][obst]));
                        toVisitBack.add(new Point(i-1,j));
                        obstaclesBack.add(obst+grid[i-1][j]);
                    }
                }
                if(j+1<n){
                    if(grid[i][j+1]+obst<=k){
                        memoBack[i][j+1][obst+grid[i][j+1]]=Math.min(memoBack[i][j+1][obst+grid[i][j+1]],plus(memoBack[i][j][obst]));
                        toVisitBack.add(new Point(i,j+1));
                        obstaclesBack.add(obst+grid[i][j+1]);
                    }
                }
                if(j-1>=0){
                    if(grid[i][j-1]+obst<=k){
                        memoBack[i][j-1][obst+grid[i][j-1]]=Math.min(memoBack[i][j-1][obst+grid[i][j-1]],plus(memoBack[i][j][obst]));
                        toVisitBack.add(new Point(i,j-1));
                        obstaclesBack.add(obst+grid[i][j-1]);
                    }
                }
            }
        }
        
        int min=Integer.MAX_VALUE;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                for(int l=0;l<k+1;l++){
                    min=Math.min(min,plus(memoForw[i][j][l],memoBack[i][j][k-l]));
                }
            }
        }
        
        if(min==Integer.MAX_VALUE){
            return -1;
        }
        else{
            return min;
        }
    }
    
    int plus(int x){
        if(x==Integer.MAX_VALUE){
            return x;
        }
        else{
            return x+1;
        }
    }
    int plus(int x,int y){
        if(x==Integer.MAX_VALUE || y==Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        else{
            return x+y;
        }
    }
}