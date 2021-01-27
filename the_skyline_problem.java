/*
The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]*/

class Solution {
    public class building implements Comparable<building>{
        public int left;
        public int right;
        public int height;
        
        public building(int x,int y, int h){
            left=x;
            right=y;
            height=h;
        }
        
        public int compareTo(building other){
            if(other.left<left){
                return 1;
            }
            else if(other.left>left){
                return -1;
            }
            else{
                if(other.right<right){
                    return 1;
                }
                else if(other.right>right){
                    return -1;
                }
                else{
                    if(other.height>height){
                       return -1;
                    }
                    else if(other.height<height){
                        return 1;
                    }
                    else{return 0;}
                }
            }
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<building> heap=new PriorityQueue<building>();
        for(int i=0;i<buildings.length;i++){
            building temp=new building(buildings[i][0],buildings[i][1],buildings[i][2]);
            heap.add(temp);
        }
        List<List<Integer>> output=new ArrayList<List<Integer>>();
        while(!heap.isEmpty()){
            building current=heap.poll();
            ArrayList<Integer> list=new ArrayList<Integer>();
            ArrayList<Integer> list1=new ArrayList<Integer>();
            if(heap.isEmpty()){
                list.add(current.left);
                list.add(current.height);
                output.add(list);
                list1.add(current.right);
                list1.add(0);
                output.add(list1);
            }
            else{
                building next=heap.poll();
                if(current.right<next.left || current.right==next.left && current.height!=next.height){
                    list.add(current.left);
                    list.add(current.height);
                    output.add(list);
                    if(current.right!=next.left){
                        list1.add(current.right);
                        list1.add(0);
                        output.add(list1);
                        
                    }
                    heap.add(next);
                }
                else{
                    if(next.height<current.height){
                        if(next.right>current.right){
                            next.left=current.right;
                            heap.add(next);
                            heap.add(current);
                        }
                        else{
                            heap.add(current);
                        }
                    }
                    else if(next.height==current.height){
                        current.right=Math.max(current.right,next.right);
                        heap.add(current);
                    }
                    else{
                        if(next.left>current.left){
                            if(current.right<=next.right){
                                list.add(current.left);
                                list.add(current.height);
                                output.add(list);
                                heap.add(next);
                            }
                            else{
                                building leftB=new building(current.left,next.left,current.height);
                                building rightB=new building(next.right,current.right,current.height);
                                heap.add(leftB);
                                heap.add(rightB);
                                heap.add(next);
                                
                            }
                        }
                        else{
                            if(next.right>=current.right){
                                heap.add(next);
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
}